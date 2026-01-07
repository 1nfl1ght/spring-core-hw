package com.bank.app.command;

import com.bank.app.console.OperationType;
import com.bank.app.model.User;
import com.bank.app.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateUserCommand implements OperationCommand{

    private final UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("Enter login for new user:");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine().trim();

        User user = userService.createUser(login);
        System.out.println("User created: " + user);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.USER_CREATE;
    }
}
