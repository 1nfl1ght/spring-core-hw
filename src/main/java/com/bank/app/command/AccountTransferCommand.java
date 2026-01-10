package com.bank.app.command;

import com.bank.app.console.OperationType;
import com.bank.app.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountTransferCommand implements OperationCommand{

    private final AccountService accountService;

    public AccountTransferCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter source account ID:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);

        int fromId;
        try {
            fromId = Integer.parseInt(scanner.nextLine());
            if (fromId <= 0) {
                throw new IllegalArgumentException("Account ID must be greater than 0");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid account ID format");
        }

        System.out.println("Enter target account ID:");
        System.out.print("> ");
        int toId;
        try {
            toId = Integer.parseInt(scanner.nextLine());
            if (toId <= 0) {
                throw new IllegalArgumentException("Account ID must be greater than 0");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid account ID format");
        }

        if (fromId == toId) {
            throw new IllegalArgumentException("Enter different account ids");
        }

        System.out.println("Enter amount to transfer:");
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

        accountService.transfer(fromId, toId, amount);

        System.out.println("Amount " + amount + " transferred from account ID " + fromId + " to account ID " + toId + ".");
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_TRANSFER;
    }
}
