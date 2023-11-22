package com.adilet.todolist.controller;

import com.adilet.todolist.dto.TaskDto;
import com.adilet.todolist.entity.Task;
import com.adilet.todolist.service.TagService;
import com.adilet.todolist.service.TaskListService;
import com.adilet.todolist.service.TaskService;
import com.adilet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final TagService tagService;
    private final TaskListService taskListService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody TaskDto.Request.Create taskDto, Principal principal) {
        log.info("Create task for user {}", principal.getName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        Task task = TaskDto.Request.Create.toTask(taskDto);
        Integer taskListId = taskDto.getTaskListId();
        Collection<Integer> tagIds = taskDto.getTagIds();
        if (tagIds != null) {
            for (Integer id : tagIds.stream().toList()) {
                task.getTags().add(tagService.findById(id));
            }
        }
        if (taskListId != null) {
            task.setTaskList(taskListService.findById(taskListId));
        }
        task.setCreator(userService.findByUsername(username));
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
                @RequestParam(required = false) Integer listId,
                @RequestParam(required = false) String tags) {
        if (tags == null && listId == null) {
            return ResponseEntity.ok(taskService.findAll());
        }
        tags = tags != null ? tags : "";
        List<String> tagList = List.of(tags.split(","));
        return ResponseEntity.ok(taskService.findByTagsAndListId(listId, tagList));
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
