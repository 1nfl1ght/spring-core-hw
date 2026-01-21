package com.bank.app.service;

import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserAccountService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public void closeAccountById(int accountId) {
        Account account = accountRepository.closeAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account with ID " + accountId + " not found"));
        int userId = account.getUserId();
        userRepository.closeAccountBytId(accountId, userId);
    }
}
