package com.bank.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {

    private final int defaultAmount;

    public AccountProperties(@Value("${account.default-amount}") int defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public int getDefaultAmount() {
        return defaultAmount;
    }
}
