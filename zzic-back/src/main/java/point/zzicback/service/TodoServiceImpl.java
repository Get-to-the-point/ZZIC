package point.zzicback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import point.zzicback.mapper.TodoMapper;
import point.zzicback.model.Todo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;

    @Override
    public List<Todo> getTodoList() {
        return todoMapper.selectAll();
//        return List.of(); // 임시로 빈 리스트 반환
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoMapper.selectById(id);
//        return null; // 임시로 null 반환
    }

    @Override
    public void createTodo(Todo todo) {
        // 임시로 아무 작업도 하지 않음
        todoMapper.createTodo(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoMapper.updateTodo(todo);
        // 임시로 아무 작업도 하지 않음
    }

    @Override
    public void deleteTodo(Long id) {
        todoMapper.deleteTodo(id);
        // 임시로 아무 작업도 하지 않음
    }

    @Override
    public void modityDoneById(Long id) {
        todoMapper.modifyDone(id);
    }

    @Override
    public void modifyInfo(Todo todo) {
        todoMapper.modifyInfo(todo);
    }
}
