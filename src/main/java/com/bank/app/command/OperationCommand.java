package com.bank.app.command;

import com.bank.app.console.OperationType;

public interface OperationCommand {
    void execute();
    OperationType getOperationType();
}
