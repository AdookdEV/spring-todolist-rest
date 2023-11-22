package com.adilet.todolist.controller;

import com.adilet.todolist.dto.JwtResponse;
import com.adilet.todolist.dto.UserDto;
import com.adilet.todolist.entity.User;
import com.adilet.todolist.exception.AppError;
import com.adilet.todolist.service.UserService;
import com.adilet.todolist.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserDto.Request.Login loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                    loginRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(
                    new AppError("Invalid username or password", HttpStatus.UNAUTHORIZED.value()),
                    HttpStatus.UNAUTHORIZED
            );
        }
        UserDetails user = userService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtils.generateToken(user);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody UserDto.Request.Create userDto) {
        User user = UserDto.Request.Create.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return ResponseEntity.ok("The user registered successfully");
    }
}
