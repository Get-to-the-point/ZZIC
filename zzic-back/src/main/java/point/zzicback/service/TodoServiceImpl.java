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
        return this.todoMapper.selectAll();
    }

    @Override
    public Todo getTodoById(Long id) {
        // 임시로 null 반환
        return this.todoMapper.selectByPrimaryKey(id.longValue());
    }

    @Override
    public void createTodo(Todo todo) {
        this.todoMapper.insert(todo);   // Todo 객체를 DB에 저장
    }

    @Override
    public void updateTodo(Todo todo) {
        this.todoMapper.update(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        // 임시로 아무 작업도 하지 않음
    }
}
