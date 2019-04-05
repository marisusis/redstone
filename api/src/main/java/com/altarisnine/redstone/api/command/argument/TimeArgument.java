package com.altarisnine.redstone.api.command.argument;

import com.altarisnine.redstone.api.util.Time;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class TimeArgument extends Argument<Time> {

    private static final List<String> VALUES = ImmutableList.<String>builder()
            .add("12am")
            .add("6am")
            .add("12pm")
            .add("6pm")
            .build();

    public TimeArgument(String key) {
        super(key);
    }

    protected TimeArgument(String key, boolean required) {
        super(key, required);
    }

    @Override
    public boolean validate(String input) {
        // Validate the time
        return Time.validateTime(input);
    }

    @Override
    public Time parseArgument(String argument) throws ArgumentParseException {
        try {
            return Time.parseTime(argument);
        } catch (IllegalStateException e) {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        return VALUES;
    }

    @Override
    public String getErrorMessage() {
        return "That is not a valid time!";
    }
}
