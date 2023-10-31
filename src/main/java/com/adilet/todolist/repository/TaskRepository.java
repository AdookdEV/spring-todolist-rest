package com.adilet.todolist.repository;

import com.adilet.todolist.entity.Task;
import com.adilet.todolist.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Integer> {
//    List<Task> findTaskByTaskListId(Integer taskListId);
}
