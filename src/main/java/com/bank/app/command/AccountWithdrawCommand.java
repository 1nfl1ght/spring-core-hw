package com.bank.app.command;

import com.bank.app.console.OperationType;
import com.bank.app.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountWithdrawCommand implements OperationCommand {

    private final AccountService accountService;

    public AccountWithdrawCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(Scanner scanner) {

        System.out.println("Enter account id:");
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

        System.out.println("Enter amount to withdraw:");
        System.out.print("> ");

        int amount;
        try {
            amount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount format");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount can not be less than 0");
        }

        accountService.accountWithdraw(accId, amount);
        System.out.println("Amount " + amount + " withdrawn to account ID: " + accId);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_WITHDRAW;
    }
}
