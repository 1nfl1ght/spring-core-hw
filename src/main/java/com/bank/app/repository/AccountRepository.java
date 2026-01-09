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

    public void accountDeposit(int accId, int amount) {
        Account account = accountList.stream()
                .filter(acc -> acc.getId() == accId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Account with ID " + accId + " not found"
                ));;

        account.setMoneyAmount(account.getMoneyAmount() + amount);
    }

    public void accountWithdraw(int accId, int amount) {
        Account account = accountList.stream()
                .filter(acc -> acc.getId() == accId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Account with ID " + accId + " not found"
                ));

        if (account.getMoneyAmount() - amount < 0) {
            throw new IllegalArgumentException("Not enough money on the account with ID: " + account.getId());
        } else {
            account.setMoneyAmount(account.getMoneyAmount() - amount);
        }
    }

    public void transfer(int fromId, int toId, int amount) {
        Account accFrom = accountList.stream()
                .filter(acc -> acc.getId() == fromId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Account with ID " + fromId + " not found"
                ));;

        Account accTo = accountList.stream()
                .filter(acc -> acc.getId() == toId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Account with ID " + toId + " not found"
                ));

        if (accFrom.getMoneyAmount() - amount < 0) {
            throw new IllegalArgumentException("Not enough money in the account with id: " + accFrom.getId());
        } else {
            accFrom.setMoneyAmount(accFrom.getMoneyAmount() - amount);
        }
        accTo.setMoneyAmount(accTo.getMoneyAmount() + amount);
    }

}
