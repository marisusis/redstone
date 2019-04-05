package com.altarisnine.redstone.api.command.exception;

import com.altarisnine.redstone.api.command.CommandNode;

public final class CommandException extends RuntimeException {
    public CommandException(CommandNode node) {
        super("Something went wrong with CommandNode[" + node.getName() + ']');
    }
}
