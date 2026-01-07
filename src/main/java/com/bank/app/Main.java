package com.bank.app;

import com.bank.app.config.BankAppConfig;
import com.bank.app.console.OperationsConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BankAppConfig.class);

        OperationsConsoleListener listener = context.getBean(OperationsConsoleListener.class);
        listener.listen();
    }
}