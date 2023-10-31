package com.adilet.todolist.repository;

import com.adilet.todolist.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
}
