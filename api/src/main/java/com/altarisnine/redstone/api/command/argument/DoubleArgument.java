package com.altarisnine.redstone.api.command.argument;

import java.util.Collections;
import java.util.List;

public final class DoubleArgument extends Argument<Double> {

    public DoubleArgument(String key) {
        super(key);
    }

    @Override
    public Double parseArgument(String argument) throws ArgumentParseException {
        try {
            return Double.parseDouble(argument);
        } catch (Exception e) {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        return Collections.emptyList();
    }

    @Override
    public boolean validate(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return "That is not a valid double";
    }
}
