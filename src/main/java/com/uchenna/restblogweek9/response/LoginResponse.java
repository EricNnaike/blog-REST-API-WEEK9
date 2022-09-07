package com.uchenna.restblogweek9.response;

import com.uchenna.restblogweek9.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private LocalDateTime timeStamp;
}
