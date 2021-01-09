package com.teo.restaurants.service;

import com.teo.restaurants.model.User;
import com.teo.restaurants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void signUp(User user){
        userRepository.save(user);
    }
}
