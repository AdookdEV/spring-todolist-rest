package com.adilet.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="TASK_LIST")
public class TaskList {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
