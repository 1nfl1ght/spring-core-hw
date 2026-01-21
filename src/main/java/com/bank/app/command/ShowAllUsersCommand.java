package com.bank.app.command;

import com.bank.app.console.OperationType;
import com.bank.app.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class ShowAllUsersCommand implements OperationCommand{

    private final UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("List of all users:");
        userService.findAll()
                .forEach(System.out::println);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.SHOW_ALL_USERS;
    }
}
