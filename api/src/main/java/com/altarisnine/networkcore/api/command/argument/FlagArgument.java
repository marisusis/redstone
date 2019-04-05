package com.altarisnine.networkcore.api.command.argument;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.guard.flag.Flag;
import com.altarisnine.networkcore.api.guard.flag.FlagNotFoundException;
import com.altarisnine.networkcore.api.guard.flag.Flags;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class FlagArgument extends Argument<Flag<?>> {
    public FlagArgument(String key) {
        super(key);
    }

    public FlagArgument(String key, boolean required) {
        super(key, required);
    }

    @Override
    public Flag parseArgument(String argument) throws ArgumentParseException {
        try {
            Flag<?> flag = Core.getApi().getGuard().getFlagByName(argument.toLowerCase());
            return flag;
        } catch (FlagNotFoundException e) {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public boolean validate(String input) {
        return Flags.flags.containsKey(input);
    }

    @Override
    public List<String> getAvailableValues() {
        return ImmutableList.copyOf(Flags.flags.keySet());
    }

    @Override
    public String getErrorMessage() {
        return "That flag doesn't exist!";
    }
}
