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

    @ManyToOne
    @JoinColumn(name="task_list_id")
    private TaskList taskList;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private  User creator;
}
