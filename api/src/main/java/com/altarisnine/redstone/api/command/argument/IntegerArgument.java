package com.altarisnine.redstone.api.command.argument;

import java.util.Collections;
import java.util.List;

public final class IntegerArgument extends Argument<Integer> {

    public IntegerArgument(String key) {
        super(key);
    }

    @Override
    public boolean validate(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Integer parseArgument(String argument) throws ArgumentParseException {
        try {
            return Integer.parseInt(argument);
        } catch (Exception e) {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        return Collections.emptyList();
    }

    @Override
    public String getErrorMessage() {
        return "That is not a valid integer";
    }
}
