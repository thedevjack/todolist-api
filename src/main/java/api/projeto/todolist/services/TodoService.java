package api.projeto.todolist.services;


import api.projeto.todolist.model.Todo;
import api.projeto.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
        List<Todo> getAll = todoRepository.findAll();
        return getAll;
    }

    public Todo create(Todo todo){
        Todo newTodo = todoRepository.save(todo);
        return newTodo;
    }

//    public Optional<Todo> findById(Long id){
//        Optional<Todo> getId = todoRepository.findById(id);
//        return getId;
//    }

    public Todo update(Todo todo){
        Todo updateTodo = todoRepository.save(todo);
        return updateTodo;
    }

    public boolean existById(Long id){
        boolean existTodo = todoRepository.existsById(id);
        return existTodo;
    }

}
