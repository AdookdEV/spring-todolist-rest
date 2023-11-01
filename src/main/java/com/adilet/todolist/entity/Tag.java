package com.adilet.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
