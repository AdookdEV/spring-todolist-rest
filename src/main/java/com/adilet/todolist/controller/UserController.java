package com.adilet.todolist.controller;

import com.adilet.todolist.dto.UserDto;
import com.adilet.todolist.entity.User;
import com.adilet.todolist.mapper.UserMapper;
import com.adilet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(UserMapper.toDto(userService.findById(id)));
    }

    @PostMapping("/disable/{id}")
    public ResponseEntity<String> disableUser(@PathVariable Integer id) {
        userService.disableUser(id);
        return ResponseEntity.ok("The user has been disabled");
    }
}
