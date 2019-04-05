package com.altarisnine.redstone.api.command.argument;

public class ArgumentParseException extends Exception {

    public ArgumentParseException(String message) {
        super(message);
    }

    public ArgumentParseException(Argument argument) {
        super(argument.getErrorMessage());
    }
}
