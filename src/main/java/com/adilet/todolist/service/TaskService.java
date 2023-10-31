package com.adilet.todolist.service;

import com.adilet.todolist.entity.Task;
import com.adilet.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task findById(Integer id) {
        return taskRepository
                .findById(id)
                .orElseThrow();
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task update(Integer id, Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}
