package com.uchenna.restblogweek9.exception;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{
    private String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }
}
