package com.springboot.comodoactivetodoservice.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Todo {

  @Id
  @GeneratedValue
  private Long id;
  private String explanation;

  @Enumerated(EnumType.STRING)
  private Priority priority;

  @ManyToOne(fetch = FetchType.EAGER)
  private TodoGroup todoGroup;

  private Date dueDate;

  @Enumerated(EnumType.STRING)
  private TodoStatus status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public TodoGroup getTodoGroup() {
    return todoGroup;
  }

  public void setTodoGroup(TodoGroup todoGroup) {
    this.todoGroup = todoGroup;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public TodoStatus getStatus() {
    return status;
  }

  public void setStatus(TodoStatus status) {
    this.status = status;
  }



  @Override
  public String toString() {
    return "Todo [id=" + id + ", explanation=" + explanation + ", priority=" + priority
        + ", todoGroup=" + todoGroup + ", dueDate=" + dueDate + ", status=" + status + "]";
  }

}
