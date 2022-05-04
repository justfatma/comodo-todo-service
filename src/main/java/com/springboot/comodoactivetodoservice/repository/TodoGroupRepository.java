package com.springboot.comodoactivetodoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.comodoactivetodoservice.model.TodoGroup;


@Repository
public interface TodoGroupRepository extends JpaRepository<TodoGroup, Long> {

}
