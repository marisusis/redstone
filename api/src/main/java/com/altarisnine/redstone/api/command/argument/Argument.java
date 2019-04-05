package com.altarisnine.redstone.api.command.argument;

import com.altarisnine.redstone.api.Redstone;
import lombok.Getter;

import java.util.List;

// TODO switch to a static class for referencing core argument types ex Arguments.player
public abstract class Argument<T> {

    @Getter private final String key;
    @Getter private final boolean required;

    public Argument(String key) {
        this(key, true);
    }

    protected Argument(String key, boolean required) {
        this.key = key;
        this.required = required;
    }

    public abstract T parseArgument(String argument) throws ArgumentParseException;
    public abstract List<String> getAvailableValues();

    public boolean validate(String input) {
        Redstone.getApi().getLogger().error("Not implemented for " + this.getClass().getName() + " argument");
        return true;
    }

    public abstract String getErrorMessage();
}