package com.adilet.todolist.controller;

import com.adilet.todolist.entity.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return null;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Integer id) {
        return null;
    }

    @PutMapping()
    public Task updateTask(@RequestBody Task task) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable Integer id) {
        return null;
    }
}
