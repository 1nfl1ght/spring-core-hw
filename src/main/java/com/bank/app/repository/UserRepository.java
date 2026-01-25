package com.bank.app.repository;

import com.bank.app.config.AccountProperties;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.AccountService;
import com.bank.app.utils.TransactionHelper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final TransactionHelper transactionHelper;

    public UserRepository(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public void save(User user) {
        transactionHelper.executeInTransaction(session -> {
            session.persist(user);
        });
    }

    public User findUserByLogin(String login) {
        String sql = """
                from User where login = :login
                """;
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery(sql, User.class)
                    .setParameter("login", login)
                    .uniqueResult();
        });
    }

    public User findUserById(int id) {
        String sql = """
                from User where id = :id
                """;
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery(sql, User.class)
                    .setParameter("id", id)
                    .uniqueResult();
        });
    }

    public List<User> findAll() {
        String sql = """
                from User
                """;
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery(sql, User.class)
                    .list();
        });
    }
}
