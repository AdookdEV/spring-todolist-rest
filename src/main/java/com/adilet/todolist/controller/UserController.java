package com.adilet.todolist.controller;

import com.adilet.todolist.dto.UserDto;
import com.adilet.todolist.entity.User;
import com.adilet.todolist.service.UserService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto.Response.Private>> getAllUsers(
            @RequestParam(value = "offset", defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        return ResponseEntity.ok(userService.findAll(offset, limit)
                .stream()
                .map(UserDto.Response.Private::toDto)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto.Response.Public> getUserById(@PathVariable @Min(1) Integer id, Principal principal) {
        User user = userService.findById(id);
        if (!user.getUsername().equals(principal.getName())) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(UserDto.Response.Public.toDto(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/disable/{id}")
    public ResponseEntity<String> disableUser(@PathVariable @Min(1) Integer id, Principal principal) {
        User u = userService.findByUsername(principal.getName());
        if (u.getId().equals(id)) {
            return ResponseEntity.badRequest().body("You can't disable your own account.");
        }
        userService.disableUser(id);
        return ResponseEntity.ok("The user has been disabled");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/enable/{id}")
    public ResponseEntity<String> enableUser(@PathVariable @Min(1) Integer id) {
        userService.enableUser(id);
        return ResponseEntity.ok("The user has been enabled");
    }
}
