package api.projeto.todolist.controller;

import api.projeto.todolist.exception.BadRequestException;
import api.projeto.todolist.exception.ConflictException;
import api.projeto.todolist.exception.NotFoundException;
import api.projeto.todolist.model.Todo;
import api.projeto.todolist.repository.TodoRepository;
import api.projeto.todolist.services.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    private final TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<?> getAll(){
        if(todoService.findAll().size() > 0){
            return ResponseEntity.ok(todoService.findAll());
        }
        throw new BadRequestException("INFO: Nenhuma tarefa encontrada!");
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Todo todo) {
        if (todo.getId() != 0 && !todoService.existById(todo.getId())) {
            Todo newTodo = todoService.create(todo);
            return ResponseEntity.created(URI.create("/todo/" + newTodo.getId())).build();
        }
        throw new ConflictException("ERROR: tarefa já criada.");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid Todo todo){
        if(todo.getId() != null && todoService.existById(todo.getId())){
            try {
                todoService.create(todo);
                return ResponseEntity.ok("INFO: Tarefa atualizada!");
            } catch (Exception validaException) {
                validaException.printStackTrace();
                throw new BadRequestException("ERROR: Dados inseridos estão incorretos, verifique! (" + validaException.getMessage() + ")");
            }
        }
        throw new BadRequestException("ERROR: Tarefa não existe!");
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return ResponseEntity.ok("INFO: Excluído com sucesso!");
        }
        throw new NotFoundException("ERROR: ID invalido");
    }

}
