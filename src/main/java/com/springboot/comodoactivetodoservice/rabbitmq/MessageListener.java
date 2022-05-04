package com.springboot.comodoactivetodoservice.rabbitmq;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springboot.comodoactivetodoservice.model.Todo;
import com.springboot.comodoactivetodoservice.model.TodoGroup;
import com.springboot.comodoactivetodoservice.repository.TodoGroupRepository;
import com.springboot.comodoactivetodoservice.repository.TodoRepository;



@Component
public class MessageListener {

  @Autowired
  private TodoGroupRepository groupRepository;

  @Autowired
  private TodoRepository todoRepository;

  @RabbitListener(queues = MQConfig.QUEUE)
  public void listener(GroupMessage message) {

    TodoGroup todoGroup = new TodoGroup();

    if (message.isGroupDeleted()) {

      List<Todo> todoList = todoRepository.findAll();
      todoList =
          todoList.stream().filter(n -> n.getTodoGroup().getId().equals(message.getTodoGroupId()))
              .collect(Collectors.toList());

      todoRepository.deleteAll(todoList);

      todoGroup.setId(message.getTodoGroupId());
      groupRepository.delete(todoGroup);
      System.out.println("Todo group and its todos are deleted successfully");
    } else {
      todoGroup.setId(message.getTodoGroupId());
      todoGroup.setName(message.getTodoGroupName());
      todoGroup.setTodoUserId(message.getTodoUserId());
      groupRepository.save(todoGroup);
      System.out.println("Todo group is saved successfully");
    }
  }

}
