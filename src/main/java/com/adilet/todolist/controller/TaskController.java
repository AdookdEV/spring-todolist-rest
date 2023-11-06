package com.adilet.todolist.controller;

import com.adilet.todolist.dto.TaskCreateDto;
import com.adilet.todolist.entity.Task;
import com.adilet.todolist.mapper.TaskCreateDtoMapper;
import com.adilet.todolist.repository.TagRepository;
import com.adilet.todolist.repository.TaskRepository;
import com.adilet.todolist.service.TagService;
import com.adilet.todolist.service.TaskListService;
import com.adilet.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final TagService tagService;
    private final TaskListService taskListService;

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody TaskCreateDto taskDto) {
        Task task = TaskCreateDtoMapper.map(taskDto);
        Integer taskListId = taskDto.getTaskListId();
        List<Integer> tagIds = taskDto.getTagsIds();
        if (tagIds != null) {
            for (Integer id : tagIds) {
                task.getTags().add(tagService.findById(id));
            }
        }
        if (taskListId != null) {
            task.setTaskList(taskListService.findById(taskListId));
        }
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
        tags = tags != null ? tags : "";
        return ResponseEntity.ok(taskService.findByTagsAndListId(listId, tags.split(",")));
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
