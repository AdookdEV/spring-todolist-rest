package com.adilet.todolist.mapper;

import com.adilet.todolist.dto.UserDto;
import com.adilet.todolist.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setIsNonDisabled(user.getIsNonDisabled());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
