package com.bank.app.service;

import com.bank.app.config.AccountProperties;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.repository.AccountRepository;
import com.bank.app.utils.TransactionHelper;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountProperties accountProperties;
    private final TransactionHelper transactionHelper;

    public AccountService(AccountRepository accountRepository, AccountProperties accountProperties, TransactionHelper transactionHelper) {
        this.accountRepository = accountRepository;
        this.accountProperties = accountProperties;
        this.transactionHelper = transactionHelper;
    }

    public Account createAccount(Long userId) {
        return transactionHelper.executeInTransaction(session -> {
            User user = session.get(User.class, userId);
            if (user == null) {
                throw new IllegalArgumentException("User with id " + user + " not found");
            }
            Account account = new Account(accountProperties.getDefaultAmount(), user);
            user.getAccountList().add(account);
            session.persist(account);
            return account;
        });
    }

    public void accountDeposit(int accId, int amount) {
        transactionHelper.executeInTransaction(session -> {
            Account account = session.createQuery("from Account where id =:id", Account.class)
                    .setParameter("id", accId)
                    .uniqueResult();
            if (account == null) {
                throw new IllegalArgumentException("Account with id " + accId + " not found");
            }
            account.setMoneyAmount(account.getMoneyAmount() + amount);
        });
    }

    public void accountWithdraw(int accId, int amount) {
        transactionHelper.executeInTransaction(session -> {
            Account account = session.createQuery("from Account where id =:id", Account.class)
                    .setParameter("id", accId)
                    .uniqueResult();
            if (account == null) {
                throw new IllegalArgumentException("Account with id " + accId + " not found");
            }

            if (account.getMoneyAmount() - amount < 0) {
                throw new IllegalArgumentException("Not enough money on the account with ID: " + account.getId());
            } else {
                account.setMoneyAmount(account.getMoneyAmount() - amount);
            }
        });
    }

    public void transfer(int fromId, int toId, int amount) {
        transactionHelper.executeInTransaction(session -> {
            Account accFrom = session.createQuery("from Account where id =:id", Account.class)
                    .setParameter("id", fromId)
                    .uniqueResult();

            if (accFrom == null) {
                throw new IllegalArgumentException("Account with id " + fromId + " not found");
            }

            Account accTo = session.createQuery("from Account where id =:id", Account.class)
                    .setParameter("id", toId)
                    .uniqueResult();

            if (accTo == null) {
                throw new IllegalArgumentException("Account with id " + toId + " not found");
            }

            if (accFrom.getMoneyAmount() - amount < 0) {
                throw new IllegalArgumentException("Not enough money in the account with id: " + accFrom.getId());
            } else {
                accFrom.setMoneyAmount(accFrom.getMoneyAmount() - amount);
            }
            accTo.setMoneyAmount(accTo.getMoneyAmount() + amount);
        });
    }

    public void closeAccountById(int id) {
        Account account = accountRepository.findAccountById(id);
        if (account == null) {
            throw new IllegalArgumentException("Account with ID: " + id + " not found");
        }
        accountRepository.delete(id);
    }
}
