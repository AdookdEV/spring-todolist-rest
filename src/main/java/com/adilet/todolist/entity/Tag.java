package com.adilet.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tags")

public class Tag {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;
}
