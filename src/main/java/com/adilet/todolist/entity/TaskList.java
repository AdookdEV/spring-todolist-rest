package com.adilet.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="task_lists")
public class TaskList {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;
}
