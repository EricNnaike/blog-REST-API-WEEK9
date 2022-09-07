package com.uchenna.restblogweek9.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class UserDTO {
    private String name;
    private String email;
    private String role;
    private String password;
}
