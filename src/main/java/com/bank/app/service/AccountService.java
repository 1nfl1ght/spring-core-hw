package com.bank.app.service;

import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(int clientId) {
        Account account = accountRepository.createAccount(clientId);

        return account;
    }
}
