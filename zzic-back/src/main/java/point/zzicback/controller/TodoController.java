package point.zzicback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import point.zzicback.model.Todo;
import point.zzicback.service.TodoService;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 메인 페이지에서 할 일 목록을 조회
    @GetMapping
    public String viewTodos(Model model) {
        model.addAttribute("todos", todoService.getTodoList());
        return "todos";
    }

    // 상세페이지
    @GetMapping("/{id}")
    public String viewDetailTodo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("todos", todoService.getTodoById(id));
        return "todo_detail";
    }

    // TODO 생성
    @PostMapping
    public String createTodo(Model model, @RequestParam("title") String title, @RequestParam("description") String description) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todoService.createTodo(todo);
        model.addAttribute("todos", todoService.getTodoList());
        return "todos";
    }

    // TODO 수정
    @PatchMapping
    public String modifyDone(Model model, @RequestParam("id") Long id) {
        todoService.modityDoneById(id);
        model.addAttribute("todos", todoService.getTodoList());
        return "todos";
    }

    // TODO 삭제
    @DeleteMapping
    public String deleteTodo(Model model, @RequestParam("id") Long id) {
        todoService.deleteTodo(id);
        model.addAttribute("todos", todoService.getTodoList());
        return "todos";
    }

}
