package com.adilet.todolist.controller;

import com.adilet.todolist.dto.UserDto;
import com.adilet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto.Response.Public>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserDto.Response.Public::toDto)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto.Response.Public> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(UserDto.Response.Public.toDto(userService.findById(id)));
    }

    @PostMapping("/disable/{id}")
    public ResponseEntity<String> disableUser(@PathVariable Integer id) {
        userService.disableUser(id);
        return ResponseEntity.ok("The user has been disabled");
    }

    @PostMapping("/enable/{id}")
    public ResponseEntity<String> enableUser(@PathVariable Integer id) {
        userService.enableUser(id);
        return ResponseEntity.ok("The user has been enabled");
    }
}
