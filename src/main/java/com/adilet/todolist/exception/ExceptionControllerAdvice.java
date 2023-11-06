package com.adilet.todolist.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<?> exceptionTagNotFoundHandler() {
        return ResponseEntity.badRequest().body("There's no such tag");
    }

    @ExceptionHandler(TaskListNotFoundException.class)
    public ResponseEntity<?> exceptionTaskListNotFoundHandler() {
        return ResponseEntity.badRequest().body("There's no such task list");
    }
}
