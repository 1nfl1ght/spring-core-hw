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
        Optional<Account> account = accountList.stream()
                .filter(acc -> acc.getId() == accId)
                .findFirst();

        account.ifPresent(acc -> acc.setMoneyAmount(acc.getMoneyAmount() + amount));
    }

    public void accountWithdraw(int accId, int amount) {
        Optional<Account> account = accountList.stream()
                .filter(acc -> acc.getId() == accId)
                .findFirst();

        account.ifPresent(acc -> acc.setMoneyAmount(acc.getMoneyAmount() - amount));
    }

    public void transfer(int fromId, int toId, int amount) {
        Optional<Account> accFrom = accountList.stream()
                .filter(acc -> acc.getId() == fromId)
                .findFirst();

        Optional<Account> accTo = accountList.stream()
                .filter(acc -> acc.getId() == toId)
                .findFirst();

        accFrom.ifPresent(
                acc -> acc.setMoneyAmount(acc.getMoneyAmount() - amount)
        );
        accTo.ifPresent(
                acc -> acc.setMoneyAmount(acc.getMoneyAmount() + amount)
        );
    }

}
