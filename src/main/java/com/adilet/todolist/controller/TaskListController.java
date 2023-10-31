package com.adilet.todolist.controller;

import com.adilet.todolist.entity.Task;
import com.adilet.todolist.entity.TaskList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class TaskListController {
    @PostMapping
    public TaskList addTaskList(@RequestBody TaskList taskList) {
        return null;
    }

    @GetMapping
    public List<TaskList> getAllTaskLists() {
        return null;
    }

    @GetMapping("/{id}")
    public List<Task> getTasks(@PathVariable Integer id) {
        return null;
    }

    @PutMapping
    public TaskList updateTaskList(@RequestBody TaskList taskList) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTaskListById(@PathVariable Integer id) {
        return null;
    }
}
