package com.bank.app.repository;

import com.bank.app.config.AccountProperties;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.utils.TransactionHelper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    private final TransactionHelper transactionHelper;

    public AccountRepository(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public void save(Account account) {
        transactionHelper.executeInTransaction(session -> {
            session.persist(account);
        });
    }

    public Account findAccountById(int id) {
        String sql = """
                from Account where id = :id
                """;
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery(sql, Account.class)
                    .setParameter("id", id)
                    .uniqueResult();
        });
    }

    public void delete(int id) {
        transactionHelper.executeInTransaction(session -> {
            Account account = findAccountById(id);
            session.remove(account);
        });
    }

}
