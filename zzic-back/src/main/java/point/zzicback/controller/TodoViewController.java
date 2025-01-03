package point.zzicback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import point.zzicback.dto.request.CreateTodoRequest;
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
}
