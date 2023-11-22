package com.adilet.todolist.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppError {
    private String message;
    private Integer status;
}
