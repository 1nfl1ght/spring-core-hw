package com.bank.app.command;

import com.bank.app.console.OperationType;
import com.bank.app.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountDepositCommand implements OperationCommand{

    private final AccountService accountService;

    public AccountDepositCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter account id:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int accId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to deposit:");
        System.out.print("> ");
        int amount = Integer.parseInt(scanner.nextLine());
        accountService.accountDeposit(accId, amount);
        System.out.println("Amount " + amount + " deposited to account ID: " + accId);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_DEPOSIT;
    }
}
