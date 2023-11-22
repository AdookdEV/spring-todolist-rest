package com.adilet.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<?> exceptionTagNotFoundHandler() {
        return ResponseEntity.badRequest().body(
                new AppError("There's no such tag",
                HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(TaskListNotFoundException.class)
    public ResponseEntity<?> exceptionTaskListNotFoundHandler() {
        return ResponseEntity.badRequest().body(
                new AppError("There's no such task list",
                HttpStatus.BAD_REQUEST.value()));
    }
}
