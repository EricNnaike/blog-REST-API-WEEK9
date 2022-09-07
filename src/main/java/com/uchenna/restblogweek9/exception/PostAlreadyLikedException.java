package com.uchenna.restblogweek9.exception;

import lombok.Data;

@Data
public class PostAlreadyLikedException extends RuntimeException{
    private String message;

    public PostAlreadyLikedException(String message) {
        this.message = message;
    }
}
