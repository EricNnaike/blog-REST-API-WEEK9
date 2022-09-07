package com.uchenna.restblogweek9.exception;

import lombok.Data;

@Data
public class PostNotFoundException extends RuntimeException{
    private String message;

    public PostNotFoundException(String message) {
        this.message = message;
    }
}
