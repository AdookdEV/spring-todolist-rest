package com.adilet.todolist.service;

import com.adilet.todolist.entity.TaskList;
import com.adilet.todolist.exception.TaskListNotFoundException;
import com.adilet.todolist.repository.TaskListRepository;
import com.adilet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;

    public TaskList addTaskList(TaskList taskList, String username) {
        taskList.setCreator(userRepository.findByUsername(username).orElseThrow());
        return taskListRepository.save(taskList);
    }

    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }

    public TaskList findById(Integer id) {
        return taskListRepository.findById(id)
                .orElseThrow(TaskListNotFoundException::new);
    }

    public TaskList updateById(Integer id, TaskList taskList, String username) {
        taskList.setId(id);
        TaskList ts = findByIdAndUsername(id, username);
        if (taskList.getName() != null) {
            ts.setName(taskList.getName());
        }
        return taskListRepository.save(ts);
    }

    public void deleteById(Integer id) {
        taskListRepository.deleteById(id);
    }

    public List<TaskList> findTaskListsByUsername(String username) {
        return taskListRepository.findTaskListsByCreator_Username(username);
    }

    public TaskList findByIdAndUsername(Integer id, String username) {
        return taskListRepository.findTaskListByIdAndCreator_Username(id, username)
                .orElseThrow(() -> new TaskListNotFoundException(
                        String.format("User %s doesn't have task-list with id %d", username, id)
                ));
    }

    public void deleteByIdAndUsername(Integer id, String username) {
        taskListRepository.delete(findByIdAndUsername(id, username));
    }
}
