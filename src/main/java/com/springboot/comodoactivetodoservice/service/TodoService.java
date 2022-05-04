package com.springboot.comodoactivetodoservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.springboot.comodoactivetodoservice.model.Todo;
import com.springboot.comodoactivetodoservice.model.TodoGroup;
import com.springboot.comodoactivetodoservice.model.TodoGroupIO;
import com.springboot.comodoactivetodoservice.model.TodoIO;
import com.springboot.comodoactivetodoservice.model.TodoStatus;
import com.springboot.comodoactivetodoservice.repository.TodoRepository;



@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;


  public TodoIO getTodoById(Long todoId) {
    Optional<Todo> todoOp = todoRepository.findById(todoId);

    if (todoOp.isPresent()) {
      return fromEntityToModel(todoOp.get());
    } else {
      throw new ResourceNotFoundException("getTodoById todoId:" + todoId);
    }
  }


  public List<TodoIO> getTodoList() {

    List<TodoIO> todoIOList = new ArrayList<>();
    List<Todo> todoList = todoRepository.findAll();

    for (Todo todo : todoList) {
      if (todo.getStatus().equals(TodoStatus.ACTIVE)) {
        todoIOList.add(fromEntityToModel(todo));
      }
    }

    if (todoIOList.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {
      return todoIOList;
    }
  }


  public TodoIO saveTodo(TodoIO model) {

    Todo todo = fromModelToEntity(model);
    Todo savedTodo = todoRepository.save(todo);
    return fromEntityToModel(savedTodo);
  }


  public TodoIO updateTodo(Long id, TodoIO todoIO) {

    Todo todo = fromModelToEntity(todoIO);

    Todo currentTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        "ActiveTodoService/updateTodo : Todo Id not found. -> " + todo.getId()));

    currentTodo.setExplanation(todo.getExplanation());
    currentTodo.setDueDate(todo.getDueDate());
    currentTodo.setPriority(todo.getPriority());
    currentTodo.setStatus(todo.getStatus());

    Todo savedTodo = todoRepository.save(currentTodo);
    return fromEntityToModel(savedTodo);

  }


  public Long deleteTodo(Long todoId) {

    Todo currentTodo =
        todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException(
            "ActiveTodoService/deleteTodo : Todo Id not found. -> " + todoId));

    todoRepository.delete(currentTodo);
    return todoId;
  }

  private TodoGroup fromModelToEntity(TodoGroupIO groupIO) {

    TodoGroup group = new TodoGroup();
    group.setId(groupIO.getId());
    group.setName(groupIO.getName());
    group.setTodoUserId(groupIO.getTodoUserId());

    return group;
  }

  private TodoGroupIO fromEntityToModel(TodoGroup group) {

    TodoGroupIO groupIO = new TodoGroupIO();
    groupIO.setId(group.getId());
    groupIO.setName(group.getName());
    groupIO.setTodoUserId(group.getTodoUserId());

    return groupIO;
  }

  private Todo fromModelToEntity(TodoIO todoIO) {

    Todo todo = new Todo();
    todo.setId(todoIO.getId());
    todo.setExplanation(todoIO.getExplanation());
    todo.setDueDate(todoIO.getDueDate());
    todo.setTodoGroup(fromModelToEntity(todoIO.getTodoGroupIO()));
    todo.setPriority(todoIO.getPriority());
    todo.setStatus(todoIO.getStatus());

    return todo;
  }

  private TodoIO fromEntityToModel(Todo todo) {

    TodoIO todoIO = new TodoIO();
    todoIO.setId(todo.getId());
    todoIO.setExplanation(todo.getExplanation());
    todoIO.setDueDate(todo.getDueDate());
    todoIO.setTodoGroupIO(fromEntityToModel(todo.getTodoGroup()));
    todoIO.setPriority(todo.getPriority());
    todoIO.setStatus(todo.getStatus());

    return todoIO;
  }

  public List<TodoIO> getCompletedTodoList() {

    List<TodoIO> todoIOList = new ArrayList<>();
    List<Todo> todoList = todoRepository.findAll();

    for (Todo todo : todoList) {
      if (todo.getStatus().equals(TodoStatus.DONE)) {
        todoIOList.add(fromEntityToModel(todo));
      }
    }

    if (todoIOList.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {
      return todoIOList;
    }
  }

  public TodoIO activateCompletedTodo(Long id) {

    Todo currentTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        "TodoRepository/activateTodo : Todo Id not found. -> " + id));

    currentTodo.setStatus(TodoStatus.ACTIVE);

    Todo savedTodo = todoRepository.save(currentTodo);
    return fromEntityToModel(savedTodo);

  }

  public TodoIO completeActiveTodo(Long id) {

    Todo currentTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        "TodoRepository/completeActiveTodo : Todo Id not found. -> " + id));

    currentTodo.setStatus(TodoStatus.DONE);

    Todo savedTodo = todoRepository.save(currentTodo);
    return fromEntityToModel(savedTodo);

  }


  public List<TodoIO> filterActiveTodos(TodoIO todoIO) {

    List<TodoIO> todoIOList = new ArrayList<>();
    List<Todo> todoList = todoRepository.findAll();

    todoList =
        todoList.stream()
            .filter(n -> n.getTodoGroup().getTodoUserId().equals(
                todoIO.getTodoGroupIO().getTodoUserId()) && n.getStatus().equals(TodoStatus.ACTIVE))
            .collect(Collectors.toList());

    if (todoIO.getDueDate() != null) {
      todoList = todoList.stream()
          .filter(n -> n.getDueDate().toString().equals(todoIO.getDueDate().toString()))
          .collect(Collectors.toList());
    }

    if (todoIO.getPriority() != null) {
      todoList = todoList.stream().filter(n -> n.getPriority().equals(todoIO.getPriority()))
          .collect(Collectors.toList());
    }

    if (todoIO.getTodoGroupIO() != null && todoIO.getTodoGroupIO().getId() != null) {
      todoList = todoList.stream()
          .filter(n -> n.getTodoGroup().getId().equals(todoIO.getTodoGroupIO().getId()))
          .collect(Collectors.toList());
    }

    for (Todo todo : todoList) {
      todoIOList.add(fromEntityToModel(todo));
    }

    if (todoIOList.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {
      return todoIOList;
    }
  }
}
