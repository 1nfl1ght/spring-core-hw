package com.bank.app.repository;

import com.bank.app.config.AccountProperties;
import com.bank.app.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    private final List<Account> accountList = new ArrayList<>();
    private int idCounter = 0;
    private final AccountProperties accountProperties;

    public AccountRepository(AccountProperties accountProperties) {
        this.accountProperties = accountProperties;
    }

    public Account createAccount(int userId) {
        idCounter++;
        Account account = new Account(idCounter, userId, accountProperties.getDefaultAmount());
        accountList.add(account);

        return account;
    }

    public Optional<Account> closeAccountById(int accountId) {
        Optional<Account> accountToClose = accountList.stream()
                .filter(acc -> acc.getId() == accountId)
                .findFirst();

        accountToClose.ifPresent(accountList::remove);
        return accountToClose;
    }

}
