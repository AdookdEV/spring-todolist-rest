package com.adilet.todolist.controller;

import com.adilet.todolist.dto.TaskDto;
import com.adilet.todolist.entity.Task;
import com.adilet.todolist.service.TagService;
import com.adilet.todolist.service.TaskListService;
import com.adilet.todolist.service.TaskService;
import com.adilet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TagService tagService;
    private final TaskListService taskListService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody TaskDto.Request.Create taskDto, Principal principal) {
        String username = principal.getName();
        Task task = TaskDto.Request.Create.toTask(taskDto);
        Integer taskListId = taskDto.getTaskListId();
        Collection<Integer> tagIds = taskDto.getTagIds();

        if (tagIds != null) {
            for (Integer id : tagIds.stream().toList()) {
                task.getTags().add(tagService.findByIdAndUsername(id, username));
            }
        }
        if (taskListId != null) {
            task.setTaskList(taskListService.findByIdAndUsername(taskListId, username));
        }
        task.setCreator(userService.findByUsername(username));
        return new ResponseEntity<>(taskService.addTask(task, username), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id, Principal principal) {
        Task task = taskService.findById(id);
        if (!task.getCreator().getUsername().equals(principal.getName())) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
                @RequestParam(required = false) Integer listId,
                @RequestParam(required = false) String tags,
                Principal principal) {
        String username = principal.getName();
        if (tags == null && listId == null) {
            return ResponseEntity.ok(taskService.findTasksByUsername(username));
        }
        tags = tags != null ? tags : "";
        List<String> tagList = List.of(tags.split(","));
        List<Task> tasks = taskService.findByTagsAndListId(listId, tagList, username);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Integer id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
