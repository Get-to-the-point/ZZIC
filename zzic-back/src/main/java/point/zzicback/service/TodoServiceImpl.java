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
        this.todoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateDone(Todo todo, boolean done) {
        todo.setDone(done);
        int updateRows = this.todoMapper.updateDone(todo);
        if(updateRows == 0) {
            throw new RuntimeException("업데이트 할 Todo가 없습니다.");
        }
    }

}
