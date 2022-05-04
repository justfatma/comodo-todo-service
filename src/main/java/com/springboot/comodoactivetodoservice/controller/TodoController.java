package com.springboot.comodoactivetodoservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.comodoactivetodoservice.model.TodoIO;
import com.springboot.comodoactivetodoservice.service.TodoService;

@RestController
public class TodoController {

  @Autowired
  private TodoService todoService;

  @GetMapping("todos")
  public ResponseEntity<List<TodoIO>> getTodoList() {

    return new ResponseEntity<>(todoService.getTodoList(), HttpStatus.OK);
  }

  @GetMapping("todos/{todoId}")
  public ResponseEntity<TodoIO> getTodoById(@PathVariable Long todoId) {

    return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
  }

  @PostMapping("todos")
  public ResponseEntity<TodoIO> saveTodo(@RequestBody TodoIO todoIO) {

    return new ResponseEntity<>(todoService.saveTodo(todoIO), HttpStatus.CREATED);
  }


  @PutMapping("todos/{todoId}")
  public ResponseEntity<TodoIO> updateTodo(@PathVariable Long todoId, @RequestBody TodoIO todoIO) {

    return new ResponseEntity<>(todoService.updateTodo(todoId, todoIO), HttpStatus.OK);
  }

  @PutMapping("donetodo/{todoId}")
  public ResponseEntity<TodoIO> completeActiveTodo(@PathVariable Long todoId) {

    return new ResponseEntity<>(todoService.completeActiveTodo(todoId), HttpStatus.OK);
  }

  // @GetMapping("filtertodos")
  // public ResponseEntity<List<TodoIO>> filterTodos(@PathVariable Long userId,
  // @PathVariable String priority, @PathVariable Long groupId, @PathVariable Date dueDate) {
  //
  // return new ResponseEntity<>(todoService.filterActiveTodos(userId, priority, groupId, dueDate),
  // HttpStatus.OK);
  // }

  @GetMapping("filtertodos")
  public ResponseEntity<List<TodoIO>> filterTodos(@RequestBody TodoIO todoIO) {
    return new ResponseEntity<>(todoService.filterActiveTodos(todoIO), HttpStatus.OK);
  }


  @DeleteMapping("todos/{todoId}")
  public ResponseEntity<String> deleteTodo(@PathVariable Long todoId) {
    Long id = todoService.deleteTodo(todoId);
    return new ResponseEntity<>("Todo deleted successfully! id: " + id, HttpStatus.OK);
  }


  @GetMapping("completedtodos")
  public ResponseEntity<List<TodoIO>> getCompletedTodoList() {
    return new ResponseEntity<>(todoService.getCompletedTodoList(), HttpStatus.OK);
  }

  @PutMapping("completedtodos/{todoId}")
  public ResponseEntity<TodoIO> activateCompletedTodo(@PathVariable Long todoId) {

    return new ResponseEntity<>(todoService.activateCompletedTodo(todoId), HttpStatus.OK);
  }



}
