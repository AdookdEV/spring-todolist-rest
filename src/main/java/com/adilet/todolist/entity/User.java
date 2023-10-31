package com.adilet.todolist.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="\"USER\"")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Boolean isNonDisabled;
}
