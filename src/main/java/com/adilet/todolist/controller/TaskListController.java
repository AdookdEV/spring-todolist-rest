package com.adilet.todolist.controller;

import com.adilet.todolist.entity.TaskList;
import com.adilet.todolist.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lists")
public class TaskListController {
    private final TaskListService taskListService;

    @PostMapping
    public ResponseEntity<TaskList> addTaskList(@RequestBody TaskList taskList) {
        return new ResponseEntity<>(taskListService.addTaskList(taskList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getAllTaskLists() {
        return ResponseEntity.ok(taskListService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskListService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Integer id, @RequestBody TaskList taskList) {
        return ResponseEntity.ok(taskListService.updateById(id, taskList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskListById(@PathVariable Integer id) {
        taskListService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
