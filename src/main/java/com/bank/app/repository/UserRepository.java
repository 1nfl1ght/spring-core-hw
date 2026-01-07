package com.bank.app.repository;

import com.bank.app.config.AccountProperties;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.AccountService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> userList = new ArrayList<>();
    private int idCounter = 0;
    private final AccountService accountService;

    public UserRepository(AccountService accountService) {
        this.accountService = accountService;
    }

    public User createUser(String login) {
        List<Account> accountList = new ArrayList<>();
        idCounter++;
        Account account = accountService.createAccount(idCounter);
        accountList.add(account);
        User user = new User(idCounter, login, accountList);
        userList.add(user);

        return user;
    }
}
