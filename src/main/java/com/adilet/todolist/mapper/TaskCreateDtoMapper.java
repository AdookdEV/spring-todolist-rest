package com.adilet.todolist.mapper;


import com.adilet.todolist.dto.TaskCreateDto;
import com.adilet.todolist.entity.Task;

import java.util.ArrayList;

public class TaskCreateDtoMapper {
    public static Task map(TaskCreateDto taskDto) {
        return Task.builder()
                .header(taskDto.getHeader())
                .description(taskDto.getDescription())
                .deadline(taskDto.getDeadline())
                .tags(new ArrayList<>())
                .isDone(taskDto.getIsDone())
                .createdAt(taskDto.getCreatedAt())
                .build();
    }
}
