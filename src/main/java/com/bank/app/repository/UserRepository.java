package com.bank.app.repository;

import com.bank.app.model.User;
import com.bank.app.utils.TransactionHelper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<User> findUserByLogin(String login) {
        String sql = "SELECT u FROM User u WHERE u.login = :login";
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery(sql, User.class)
                    .setParameter("login", login)
                    .list();
        });
    }

    public User findUserById(Long id) {
        String sql = """
                SELECT u FROM User u where id = :id
                """;
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery(sql, User.class)
                    .setParameter("id", id)
                    .uniqueResult();
        });
    }

    public List<User> findAll() {
        String sql = """
                SELECT u FROM User u LEFT JOIN FETCH u.accountList
                """;
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery(sql, User.class)
                    .list();
        });
    }
}
