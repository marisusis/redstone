package com.altarisnine.networkcore.api.command.exception;

import com.altarisnine.networkcore.api.command.CommandNode;
import lombok.Getter;

public class InvalidCommandException extends RuntimeException {
    @Getter private final CommandNode command;

    public InvalidCommandException(CommandNode command, String message) {
        super(message);
        this.command = command;
    }
}
