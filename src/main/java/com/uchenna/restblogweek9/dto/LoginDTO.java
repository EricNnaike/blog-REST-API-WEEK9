package com.uchenna.restblogweek9.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String name;
    private String email;
    private String password;
}
