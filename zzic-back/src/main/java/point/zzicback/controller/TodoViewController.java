package point.zzicback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import point.zzicback.dto.request.CreateTodoRequest;
import point.zzicback.dto.request.UpdateTodoRequest;
import point.zzicback.model.Todo;
import point.zzicback.service.TodoService;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoViewController {

    private final TodoService todoService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String viewTodos(Model model){
        model.addAttribute("todos",todoService.getTodoList());
        return "todos";
    }

    @GetMapping("/is")
    public String viewTodoJs(){
         return "todos-js";
    }

    @PostMapping
    public String create(CreateTodoRequest createTodoRequest){
    Todo todo = objectMapper.convertValue(createTodoRequest, Todo.class);
    todo.setDone(false);
     todoService.createTodo(todo);
     return "redirect:/todos";
    }

    @GetMapping("/{id}")
    public String getTodo(Model model, @PathVariable Long id){
        model.addAttribute("todo",todoService.getTodoById(id));
        return "todo-detail";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
    @PutMapping(value = "/{id}")
    public String update (@PathVariable Long id, @RequestParam String title , @RequestParam String description,
                          @RequestParam boolean done){ //여기서 done은 필요없어도 되니깐 빼도 된다 그러려면 html 폼에서도 빼자
        Todo todo = Todo.builder()
                .id(id)
                .title(title)
                .description(description)
                .done(done)
                .build();
        todoService.updateTodo(todo);
        return "redirect:/todos";
    }
}
