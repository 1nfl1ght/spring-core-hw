package com.bank.app.command;

import com.bank.app.console.OperationType;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.AccountService;
import com.bank.app.service.UserAccountService;
import com.bank.app.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCloseCommand implements OperationCommand{

    private final UserService userService;
    private final AccountService accountService;
    private final UserAccountService userAccountService;

    public AccountCloseCommand(UserService userService, AccountService accountService, UserAccountService userAccountService) {
        this.userService = userService;
        this.accountService = accountService;
        this.userAccountService = userAccountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter account ID to close:");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine().trim();

        userAccountService.closeAccountById(Integer.parseInt(id));
        System.out.println("Account with ID: " + id + " has been closed");
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_CLOSE;
    }
}
