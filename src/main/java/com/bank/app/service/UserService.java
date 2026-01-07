package com.bank.app.service;

import com.bank.app.model.User;
import com.bank.app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String login) {
        User user = userRepository.createUser(login);

        return user;
    }
}
