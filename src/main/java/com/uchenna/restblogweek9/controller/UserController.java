package com.uchenna.restblogweek9.controller;

import com.uchenna.restblogweek9.dto.UserDTO;
import com.uchenna.restblogweek9.response.RegisterResponse;
import com.uchenna.restblogweek9.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public RegisterResponse register(@RequestBody UserDTO userDTO) {
        log.info("Successfully registered {} ", userDTO.getEmail());
        return userService.register(userDTO);
    }

}
