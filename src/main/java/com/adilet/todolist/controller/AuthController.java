package com.adilet.todolist.controller;

import com.adilet.todolist.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signin")
    public String login(@RequestBody User user) {
        return null;
    }

    @PostMapping("/signup")
    public String register() {
        return null;
    }
}
