package com.adilet.todolist.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppError {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private Integer status;
}
