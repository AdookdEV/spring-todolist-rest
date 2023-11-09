package com.adilet.todolist.dto;

import com.adilet.todolist.entity.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private Boolean isNonDisabled = false;
    private Collection<Role> roles;
}
