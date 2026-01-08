package com.bank.app.command;

import com.bank.app.console.OperationType;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.AccountService;
import com.bank.app.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCreateCommand implements OperationCommand{

    private final AccountService accountService;
    private final UserService userService;

    public AccountCreateCommand(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("Enter the user id for which to create an account: ");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine().trim();
        User user = userService.findUserById(Integer.parseInt(id));
        Account account = accountService.createAccount(Integer.parseInt(id));
        user.getAccountList().add(account);
        System.out.println("New account created with ID: " + account.getId() + " for user: " + user.getLogin());
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_CREATE;
    }
}
