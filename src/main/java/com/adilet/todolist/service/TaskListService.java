package com.adilet.todolist.service;

import com.adilet.todolist.entity.TaskList;
import com.adilet.todolist.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;

    public TaskList addTaskList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }

    public TaskList findById(Integer id) {
        return taskListRepository.findById(id).orElseThrow();
    }

    public TaskList updateById(Integer id, TaskList taskList) {
        taskList.setId(id);
        return taskListRepository.save(taskList);
    }

    public void deleteById(Integer id) {
        taskListRepository.deleteById(id);
    }
}
