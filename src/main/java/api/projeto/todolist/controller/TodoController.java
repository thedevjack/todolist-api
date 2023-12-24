package api.projeto.todolist.controller;


import api.projeto.todolist.exception.BadRequestException;
import api.projeto.todolist.repository.TodoRepository;
import api.projeto.todolist.services.TodoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;


    @GetMapping
    public ResponseEntity<?> getAll(){
        if(todoService.findAll().size() > 0){
            return ResponseEntity.ok(todoService.findAll());
        }
        throw new BadRequestException("INFO: Nenhuma tarefa encontrada!");
    }

}
