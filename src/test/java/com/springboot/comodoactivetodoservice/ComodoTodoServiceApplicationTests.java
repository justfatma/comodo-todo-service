package com.springboot.comodoactivetodoservice;



import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import com.springboot.comodoactivetodoservice.model.Priority;
import com.springboot.comodoactivetodoservice.model.TodoGroup;
import com.springboot.comodoactivetodoservice.model.TodoGroupIO;
import com.springboot.comodoactivetodoservice.model.TodoIO;
import com.springboot.comodoactivetodoservice.model.TodoStatus;
import com.springboot.comodoactivetodoservice.repository.TodoGroupRepository;
import com.springboot.comodoactivetodoservice.service.TodoService;

@SpringBootTest
class ComodoTodoServiceApplicationTests {

  @Autowired
  private TodoService todoService;

  @Autowired
  private TodoGroupRepository groupRepository;

  public TodoIO setUp() {

    TodoIO todoIO = new TodoIO();
    todoIO.setExplanation("Prepare Report");
    todoIO.setDueDate(Date.valueOf("2021-05-20"));
    todoIO.setPriority(Priority.HIGH);
    todoIO.setStatus(TodoStatus.ACTIVE);


    TodoGroup group = new TodoGroup();
    group.setId(99L);
    group.setName("Work");
    group.setTodoUserId(1000L);

    groupRepository.save(group);

    todoIO.setTodoGroupIO(fromEntityToModel(group));

    return todoService.saveTodo(todoIO);
  }

  @Test
  void shouldGetTodoById() {

    TodoIO savedTodoIO = setUp();
    TodoIO todoIO = todoService.getTodoById(savedTodoIO.getId());

    assertAll(() -> assertEquals(Date.valueOf("2021-05-20"), todoIO.getDueDate()),
        () -> assertEquals("Prepare Report", todoIO.getExplanation()),
        () -> assertEquals(TodoStatus.ACTIVE, todoIO.getStatus()),
        () -> assertEquals(Priority.HIGH, todoIO.getPriority()));
  }

  @Test
  void shouldGetTodoList() {

    setUp();
    List<TodoIO> list = todoService.getTodoList();
    assertTrue(list.size() > 0);
  }

  @Test
  void shouldSaveTodo() {
    TodoIO savedTodoIO = setUp();

    assertAll(() -> assertEquals(Date.valueOf("2021-05-20"), savedTodoIO.getDueDate()),
        () -> assertEquals("Prepare Report", savedTodoIO.getExplanation()),
        () -> assertEquals(TodoStatus.ACTIVE, savedTodoIO.getStatus()),
        () -> assertEquals(Priority.HIGH, savedTodoIO.getPriority()));
  }

  @Test
  void shouldUpdateTodo() {

    TodoIO savedTodoIO = setUp();
    savedTodoIO.setExplanation("Attend meeting");
    savedTodoIO.setPriority(Priority.MEDIUM);

    TodoIO updatedTodoIO = todoService.updateTodo(savedTodoIO.getId(), savedTodoIO);

    assertAll(() -> assertEquals("Attend meeting", updatedTodoIO.getExplanation()),
        () -> assertEquals(Priority.MEDIUM, updatedTodoIO.getPriority()));
  }

  @Test
  void shouldDeleteTodo() {

    TodoIO savedTodoIO = setUp();

    todoService.deleteTodo(savedTodoIO.getId());

    try {
      todoService.getTodoById(savedTodoIO.getId());
    } catch (ResourceNotFoundException e) {
      assertEquals("getTodoById todoId:" + savedTodoIO.getId(), e.getMessage());
    }

  }


  private TodoGroupIO fromEntityToModel(TodoGroup group) {

    TodoGroupIO groupIO = new TodoGroupIO();
    groupIO.setId(group.getId());
    groupIO.setName(group.getName());
    groupIO.setTodoUserId(group.getTodoUserId());

    return groupIO;
  }

}
