package com.bank.app.console;

import com.bank.app.command.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationsConsoleListener {

    private final Map<OperationType, OperationCommand> commandMap = new HashMap<>();


    public OperationsConsoleListener(List<OperationCommand> commands) {
        commands.forEach(command -> commandMap.put(command.getOperationType(), command));
    }

    public void listen() {
        System.out.println("Please enter one operation type:");
        System.out.println("""
                -ACCOUNT_CREATE
                -SHOW_ALL_USERS
                -ACCOUNT_CLOSE
                -ACCOUNT_WITHDRAW
                -ACCOUNT_DEPOSIT
                -ACCOUNT_TRANSFER
                -USER_CREATE""");
        System.out.print("> ");


        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                OperationType operationType;
                if (isValidCommand(input)) {
                    operationType = OperationType.valueOf(input);
                } else {
                    throw new IllegalArgumentException("Command not found");
                }
                OperationCommand command = commandMap.get(operationType);

                command.execute();

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Please enter one operation type:");
            System.out.println("""
                -ACCOUNT_CREATE
                -SHOW_ALL_USERS
                -ACCOUNT_CLOSE
                -ACCOUNT_WITHDRAW
                -ACCOUNT_DEPOSIT
                -ACCOUNT_TRANSFER
                -USER_CREATE""");
            System.out.print("> ");
        }


    }

    public boolean isValidCommand(String command) {
        try {
            OperationType.valueOf(command);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
