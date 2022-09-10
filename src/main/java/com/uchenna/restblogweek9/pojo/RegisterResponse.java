package com.uchenna.restblogweek9.pojo;

import com.uchenna.restblogweek9.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class RegisterResponse {
    private String message;
    private LocalDateTime timeStamp;
    private User user;
}
