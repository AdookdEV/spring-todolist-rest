package com.adilet.todolist.controller;

import com.adilet.todolist.entity.Task;
import com.adilet.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Integer id) {
        return taskService.findById(id);
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(required = false) Integer listId,
                               @RequestParam(required = false) String tags) {
        if (listId == null && tags == null) {
            return taskService.findAll();
        }
        return taskService.findByTagsAndListId(listId, tags.split(","));
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Integer id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Integer id) {
        taskService.deleteById(id);
    }
}
