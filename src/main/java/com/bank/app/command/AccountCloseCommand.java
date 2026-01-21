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

    private final UserAccountService userAccountService;

    public AccountCloseCommand(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter account ID to close:");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        int accId;
        try {
            accId = Integer.parseInt(scanner.nextLine());
            if (accId <= 0) {
                throw new IllegalArgumentException("Account ID must be greater than 0");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid account ID format");
        }


        userAccountService.closeAccountById(accId);
        System.out.println("Account with ID: " + accId + " has been closed");
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_CLOSE;
    }
}
