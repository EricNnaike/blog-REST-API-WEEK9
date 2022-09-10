package com.uchenna.restblogweek9.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private LocalDateTime timeStamp;
}
