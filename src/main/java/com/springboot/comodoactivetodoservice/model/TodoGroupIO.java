package com.springboot.comodoactivetodoservice.model;

public class TodoGroupIO {

  private Long id;
  private String name;
  private Long todoUserId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getTodoUserId() {
    return todoUserId;
  }

  public void setTodoUserId(Long todoUserId) {
    this.todoUserId = todoUserId;
  }

  @Override
  public String toString() {
    return "TodoGroupIO [id=" + id + ", name=" + name + ", todoUserId=" + todoUserId + "]";
  }

}
