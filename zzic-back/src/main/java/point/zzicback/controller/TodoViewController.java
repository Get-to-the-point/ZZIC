package point.zzicback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import point.zzicback.dto.request.CreateTodoRequest;
import point.zzicback.mapper.TodoMapper;
import point.zzicback.model.Todo;
import point.zzicback.service.TodoService;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoViewController {

    private final TodoService todoService;
    private final ObjectMapper objectMapper;
    private final TodoMapper todoMapper;

    /**
     * 투두 리스트 보기
     * @param model
     * @return
     */
    @GetMapping
    public String viewTodos(Model model){
//        model.addAttribute("todos",todoService.getTodoList());
        model.addAttribute("todos",todoMapper.findByDone(false)); //할 일
        model.addAttribute("completedTodos",todoMapper.findByDone(true));//한 일
        return "todos";
    }

    /**
     * 투두 리스트 상세 보기
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String getTodo(Model model, @PathVariable Long id){
        model.addAttribute("todo",todoService.getTodoById(id));
        return "todo-detail";
    }

    /**
     * 투두 리스트 상세 수정 하기
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}/update")
    public String update(Model model, @PathVariable Long id){
        model.addAttribute("todo",todoService.getTodoById(id));
        return "todo-update";
    }
    
    @PostMapping("/{id}/toggle")
    public String toggleTodo(@PathVariable Long id){
        Todo todo = todoMapper.selectId(id); // 아이디를 기준으로 데이터베이스에서 가져온다
        todo.setDone(!todo.getDone()); // 가져온 DONE을 !DONE 으로 변환한다 < 여기가 문제네요.
        todoMapper.updateTodo(todo); // 데이터를 다시 넣는다
        return "redirect:/todos";
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

    @DeleteMapping("/{id}")
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
