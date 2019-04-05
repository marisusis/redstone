package com.altarisnine.networkcore.api.command.argument;

import com.altarisnine.networkcore.api.util.Time;

import java.util.Collections;
import java.util.List;

public class TimeIntervalArgument extends Argument<Time> {

    public TimeIntervalArgument(String key) {
        super(key);
    }

    protected TimeIntervalArgument(String key, boolean required) {
        super(key, required);
    }

    @Override
    public boolean validate(String input) {
        // Validate the time interval
        return Time.validateInterval(input);
    }

    @Override
    public Time parseArgument(String argument) throws ArgumentParseException {
        try {
            return Time.parseInterval(argument);
        } catch (IllegalStateException e) {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        return Collections.emptyList();
    }

    @Override
    public String getErrorMessage() {
        return "That is not a valid time!";
    }
}
