package com.teo.restaurants.service;

import com.teo.restaurants.model.User;
import com.teo.restaurants.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("save user")
    void signUp() {
        User user = new User();
        user.setFirstName("Cosmin");
        user.setLastName("Popa");
        user.setUsername("Cocosmin33");
        user.setEmail("cosmin@yahoo.com");
        user.setPassword("mypass");

        userService.signUp(user);

        Mockito.verify(userRepository).save(user);
    }
}