package com.springboot.comodoactivetodoservice.rabbitmq;

import java.util.Date;


public class GroupMessage {

  private String messageId;
  private Date messageDate;

  private Long todoGroupId;
  private String todoGroupName;
  private Long todoUserId;
  private boolean groupDeleted;

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public Date getMessageDate() {
    return messageDate;
  }

  public void setMessageDate(Date messageDate) {
    this.messageDate = messageDate;
  }

  public Long getTodoGroupId() {
    return todoGroupId;
  }

  public void setTodoGroupId(Long todoGroupId) {
    this.todoGroupId = todoGroupId;
  }

  public String getTodoGroupName() {
    return todoGroupName;
  }

  public void setTodoGroupName(String todoGroupName) {
    this.todoGroupName = todoGroupName;
  }

  public Long getTodoUserId() {
    return todoUserId;
  }

  public void setTodoUserId(Long todoUserId) {
    this.todoUserId = todoUserId;
  }

  public boolean isGroupDeleted() {
    return groupDeleted;
  }

  public void setGroupDeleted(boolean groupDeleted) {
    this.groupDeleted = groupDeleted;
  }

}
