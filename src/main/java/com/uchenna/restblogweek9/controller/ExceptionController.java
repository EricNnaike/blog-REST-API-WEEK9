package com.uchenna.restblogweek9.controller;

import com.uchenna.restblogweek9.exception.PostAlreadyLikedException;
import com.uchenna.restblogweek9.exception.PostNotFoundException;
import com.uchenna.restblogweek9.exception.UserAlreadyExistException;
import com.uchenna.restblogweek9.exception.UserNotFoundException;
import com.uchenna.restblogweek9.pojo.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> UserNotFoundException(UserNotFoundException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostAlreadyLikedException.class)
    public ResponseEntity<Object> PostAlreadyLikedException(PostAlreadyLikedException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> PostNotFoundException(PostNotFoundException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object>  UserAlreadyExistException(UserAlreadyExistException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.ALREADY_REPORTED);
    }



}
