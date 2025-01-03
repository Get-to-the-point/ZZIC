package point.zzicback.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        public Todo getTodoById (Long id){
            return this.todoMapper.selectId(id);
        }

        @Override
        public void createTodo (Todo todo){
            todoMapper.createTodo(todo);
        }

        @Override
        public void updateTodo (Todo todo){
             todoMapper.updateTodo(todo);
        }

        @Override
        public void deleteTodo (Long id){
            todoMapper.deleteTodo(id);
        }
    }
