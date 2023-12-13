package com.adilet.todolist.service;

import com.adilet.todolist.entity.Task;
import com.adilet.todolist.exception.TaskListNotFoundException;
import com.adilet.todolist.repository.TaskListRepository;
import com.adilet.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task addTask(Task task, String username) {
        Task newTask = new Task();
        BeanUtils.copyProperties(task, newTask);
        if (!task.getTaskList().getCreator().getUsername().equals(username)) {
            throw new TaskListNotFoundException(
                    String.format("User %s doesn't have task-list with id %d.",
                            username, task.getTaskList().getId())
            );
        }
        return taskRepository.save(task);
    }

    public Task findById(Integer id) {
        return taskRepository
                .findById(id)
                .orElseThrow();
    }

    public List<Task> findByTagsAndListId(Integer listId, List<String> tags, String username) {
        if (tags.isEmpty()) {
            tags.add("");
        }
        List<Task> tasks = taskRepository.findTasksByTagsAndListId(listId, tags);
        return tasks
                .stream()
                .filter(t -> t.getCreator().getUsername().equals(username))
                .toList();
    }

    public List<Task> findTasksByUsername(String username) {
        return taskRepository.findTasksByCreator_Username(username);
    }

    public Task update(Integer id, Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }


}
