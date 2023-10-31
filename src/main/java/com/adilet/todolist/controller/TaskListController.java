package com.adilet.todolist.controller;

import com.adilet.todolist.entity.TaskList;
import com.adilet.todolist.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lists")
public class TaskListController {
    private final TaskListService taskListService;

    @PostMapping
    public TaskList addTaskList(@RequestBody TaskList taskList) {
        return taskListService.addTaskList(taskList);
    }

    @GetMapping
    public List<TaskList> getAllTaskLists() {
        return taskListService.findAll();
    }

    @GetMapping("/{id}")
    public TaskList getTaskListById(@PathVariable Integer id) {
        return taskListService.findById(id);
    }

    @PutMapping("/{id}")
    public TaskList updateTaskList(@PathVariable Integer id, @RequestBody TaskList taskList) {
        return taskListService.updateById(id, taskList);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskListById(@PathVariable Integer id) {
        taskListService.deleteById(id);
    }
}
