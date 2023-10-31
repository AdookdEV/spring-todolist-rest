package com.adilet.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="TASK")
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    private String header;
    private String description;
    private Boolean isDone = false;
    private LocalDateTime deadline;
}
