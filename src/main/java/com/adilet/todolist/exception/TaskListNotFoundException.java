package com.adilet.todolist.exception;

public class TaskListNotFoundException extends RuntimeException {
    public TaskListNotFoundException() {
        super();
    }

    public TaskListNotFoundException(String message) {
        super(message);
    }
}
