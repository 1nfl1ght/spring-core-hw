package com.bank.app.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class TransactionHelper {

    private final SessionFactory sessionFactory;

    public TransactionHelper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void executeInTransaction(Consumer<Session> action) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        boolean isNewTransaction = !transaction.isActive();

        if (isNewTransaction) {
            transaction.begin();
        }
        try {
            action.accept(session);
            if (isNewTransaction && transaction.isActive()) {
                transaction.commit();
            }
        } catch (Exception e) {
            if (isNewTransaction && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public <T> T executeInTransaction(Function<Session, T> action) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        boolean isNewTransaction = !transaction.isActive();

        try {
            if (isNewTransaction) {
                transaction.begin();
            }
            T result = action.apply(session);
            if (isNewTransaction && transaction.isActive()) {
                transaction.commit();
            }
            return result;

        } catch (Exception e) {
            if (isNewTransaction && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
