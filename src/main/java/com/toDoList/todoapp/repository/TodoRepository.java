package com.toDoList.todoapp.repository;

import com.toDoList.todoapp.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
