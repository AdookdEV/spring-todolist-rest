package com.adilet.todolist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TaskCreateDto {
    private String header;
    private String description;
    private Boolean isDone = false;
    private LocalDate deadline;
    @JsonIgnore
    private LocalDate createdAt = LocalDate.now();
    private Integer taskListId;
    private List<Integer> tagsIds;
}
