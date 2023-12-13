package com.adilet.todolist.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException() {
        super();
    }

    public TagNotFoundException(String message) {
        super(message);
    }
}
