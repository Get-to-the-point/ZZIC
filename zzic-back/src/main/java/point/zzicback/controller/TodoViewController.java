package point.zzicback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import point.zzicback.dto.request.CreateTodoRequest;
import point.zzicback.dto.request.UpdateTodoRequest;
import point.zzicback.model.Todo;
import point.zzicback.service.TodoService;

@Controller
@RequiredArgsConstructor
public class TodoViewController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public String todos(Model model){   // model은 스프링이 주입해준다.
        model.addAttribute("todos", todoService.getTodoList());
        return "Todos";
    }

    @PostMapping("/todos")
    public String todoCreate(CreateTodoRequest createTodoRequest){
        Todo todo = new Todo();
        todo.setTitle(createTodoRequest.getTitle());
        todo.setDescription(createTodoRequest.getDescription());
        todoService.createTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/todos/{id}")
    public String getTodoById(Model model, @PathVariable Long id){
        model.addAttribute("todo", todoService.getTodoById(id));
        return "TodoDetail";
    }

    @PostMapping("/todos/done")
    public String done(@RequestParam("id")Long id) {
        Todo todo = todoService.getTodoById(id);
        todoService.updateDone(todo, true);
        return "redirect:/todos";
    }

    @PostMapping("/todos/undone")
    public String undone(@RequestParam("id") Long id) {
        Todo todo = todoService.getTodoById(id);
        todoService.updateDone(todo, false);
        return "redirect:/todos";
    }

    @DeleteMapping("/todos/delete")
    public String delete(@RequestParam Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }

    @GetMapping("/todos/{id}/edit")
    public String editTodoForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "TodoEditForm";
    }

    @PutMapping("/todos/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute UpdateTodoRequest updateTodoRequest) {
        Todo todo = todoService.getTodoById(id);
        todo.setTitle(updateTodoRequest.getTitle());
        todo.setDescription(updateTodoRequest.getDescription());
        todo.setDone(updateTodoRequest.getDone());
        todoService.updateTodo(todo);
        return "redirect:/todos";
    }

}
