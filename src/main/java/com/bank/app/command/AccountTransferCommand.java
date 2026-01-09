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
        int fromId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter target account ID:");
        System.out.print("> ");
        int toId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter amount to transfer:");
        System.out.print("> ");
        int amount = Integer.parseInt(scanner.nextLine());

        accountService.transfer(fromId, toId, amount);

        System.out.println("Amount " + amount + " transferred from account ID " + fromId + " to account ID " + toId + ".");
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_TRANSFER;
    }
}
