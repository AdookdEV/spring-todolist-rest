package com.adilet.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name="task_lists")
public class TaskList {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank(message = "Task-list's name may not be null")
    @Column(nullable = false)
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private  User creator;
}
