package com.uchenna.restblogweek9.service.userServiceImpl;

import com.uchenna.restblogweek9.dto.UserDTO;
import com.uchenna.restblogweek9.exception.UserAlreadyExistException;
import com.uchenna.restblogweek9.model.User;
import com.uchenna.restblogweek9.repository.UserRepository;
import com.uchenna.restblogweek9.pojo.RegisterResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should save the user when the email is not taken")
    void registerWhenEmailIsNotTaken() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.setEmail("john@doe.com");
        userDTO.setRole("admin");
        userDTO.setPassword("123456");

        RegisterResponse registerResponse = userService.register(userDTO);

        assertEquals("SUCCESS", registerResponse.getMessage());
        assertNotNull(registerResponse.getTimeStamp());
        assertNotNull(registerResponse.getUser());
    }

    @Test
    @DisplayName("Should throw an exception when the email is already taken")
    void registerWhenEmailIsAlreadyTakenThenThrowException() {
        UserDTO userDTO = new UserDTO("John Doe", "john@doe.com", "admin", "123456");
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new User()));
        assertThrows(UserAlreadyExistException.class, () -> userService.register(userDTO));
    }
}