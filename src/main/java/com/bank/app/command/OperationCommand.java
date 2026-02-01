package com.bank.app.command;

import com.bank.app.console.OperationType;

import java.util.Scanner;

public interface OperationCommand {
    void execute(Scanner scanner);
    OperationType getOperationType();
}
