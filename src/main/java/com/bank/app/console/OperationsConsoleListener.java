package com.bank.app.console;

import com.bank.app.command.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OperationsConsoleListener {

    private final Map<OperationType, OperationCommand> commandMap = new HashMap<>();


    public OperationsConsoleListener(List<OperationCommand> commands) {
        commands.forEach(command -> commandMap.put(command.getOperationType(), command));
    }

    public void listen() {
        printCommands();

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

                command.execute(scanner);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            printCommands();
        }
    }

    private boolean isValidCommand(String command) {
        try {
            OperationType.valueOf(command);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private void printCommands() {
        System.out.println("Please enter one operation type:");
        Arrays.stream(OperationType.values())
                .forEach(operation -> {
                    System.out.println("- " + operation.name());
                });
        System.out.print("> ");
    }
}
