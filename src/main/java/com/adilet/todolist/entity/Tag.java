package com.adilet.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="TAG")
public class Tag {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}