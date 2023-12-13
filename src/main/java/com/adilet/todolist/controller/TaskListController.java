package com.adilet.todolist.controller;

import com.adilet.todolist.entity.TaskList;
import com.adilet.todolist.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lists")
public class TaskListController {
    private final TaskListService taskListService;

    @PostMapping
    public ResponseEntity<TaskList> addTaskList(@RequestBody TaskList taskList, Principal principal) {
        return new ResponseEntity<>(taskListService.addTaskList(taskList, principal.getName()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getAllTaskListsForUser(Principal principal) {
        return ResponseEntity.ok(taskListService.findTaskListsByUsername(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Integer id, Principal principal) {
        return ResponseEntity.ok(taskListService.findByIdAndUsername(id, principal.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Integer id,
                                                   @RequestBody TaskList taskList,
                                                   Principal principal) {
        if (!id.equals(taskList.getId())) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(taskListService.updateById(id, taskList, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskListByIdAndUser(@PathVariable Integer id, Principal principal) {
        taskListService.deleteByIdAndUsername(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
