package com.bank.app.repository;

import com.bank.app.config.AccountProperties;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.AccountService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public User findUserById(int id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<User> findAll() {
        return userList;
    }

    public void closeAccountBytId(int accountId, int userId) {
        Optional<User> user = userList.stream()
                .filter(usr -> usr.getId() == userId)
                .findFirst();


        user.ifPresent(
                usr -> {
                    usr.getAccountList().removeIf(acc -> acc.getId() == accountId);
                }
        );
    }
}
