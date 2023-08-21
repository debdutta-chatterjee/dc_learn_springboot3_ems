package org.skg.emsbackend.controller;

import lombok.AllArgsConstructor;
import org.skg.emsbackend.dto.TodoDto;
import org.skg.emsbackend.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
@CrossOrigin("*")
public class TodoController
{
    private TodoService todoService;

    // Build Add Todo REST API

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo,
                HttpStatus.CREATED);
    }

    // Build Get Todo REST API
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todos = todoService.getAllTodos();
        //return new ResponseEntity<>(todos, HttpStatus.OK);
        return ResponseEntity.ok(todos);
    }

    // Build Update Todo REST API
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build Delete Todo REST API
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }

    // Build Complete Todo REST API
    @PatchMapping("{id}/complete")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build In Complete Todo REST API
    @PatchMapping("{id}/user")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }
}

