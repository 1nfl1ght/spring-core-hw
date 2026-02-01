package com.bank.app.command;

import com.bank.app.console.OperationType;
import com.bank.app.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCloseCommand implements OperationCommand {

    private final AccountService accountService;

    public AccountCloseCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("Enter account ID to close:");
        System.out.print("> ");
        int accId;
        try {
            accId = Integer.parseInt(scanner.nextLine());
            if (accId <= 0) {
                throw new IllegalArgumentException("Account ID must be greater than 0");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid account ID format");
        }

        accountService.closeAccountById(accId);
        System.out.println("Account with ID: " + accId + " has been closed");
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_CLOSE;
    }
}
