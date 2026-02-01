package com.bank.app.service;

import com.bank.app.config.AccountProperties;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AccountProperties accountProperties;

    public UserService(UserRepository userRepository, AccountProperties accountProperties) {
        this.userRepository = userRepository;
        this.accountProperties = accountProperties;
    }

    public User createUser(String login) {

        List<User> exist = userRepository.findUserByLogin(login);

        if (!exist.isEmpty()) {
            throw new IllegalArgumentException("User with login " + login + " already exists");
        }

        User user = new User(login);
        Account account = new Account(accountProperties.getDefaultAmount(), user);
        user.getAccountList().add(account);
        userRepository.save(user);
        return user;
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
