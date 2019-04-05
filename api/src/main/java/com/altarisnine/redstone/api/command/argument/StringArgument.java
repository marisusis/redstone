package com.altarisnine.redstone.api.command.argument;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public final class StringArgument extends Argument<String> {

    @Getter @Setter private String value;

    public StringArgument(String key) {
        super(key);
    }

    @Override
    public String parseArgument(String argument) {
        return argument;
    }

    @Override
    public List<String> getAvailableValues() {
        return Collections.emptyList();
    }

    @Override
    public String getErrorMessage() {
        return "This shouldn't happen...";
    }
}
