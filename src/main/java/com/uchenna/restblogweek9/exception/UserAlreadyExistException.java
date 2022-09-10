package com.uchenna.restblogweek9.exception;

import lombok.Data;

@Data
public class UserAlreadyExistException extends RuntimeException{
    private String message;

    public UserAlreadyExistException(String message) {
        this.message = message;
    }
}
