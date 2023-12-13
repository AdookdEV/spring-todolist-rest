package com.adilet.todolist.repository;

import com.adilet.todolist.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
    List<TaskList> findTaskListsByCreator_Username(String username);

    Optional<TaskList> findTaskListByIdAndCreator_Username(Integer id, String username);
}
