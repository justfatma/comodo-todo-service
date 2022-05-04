package com.springboot.comodoactivetodoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.comodoactivetodoservice.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
