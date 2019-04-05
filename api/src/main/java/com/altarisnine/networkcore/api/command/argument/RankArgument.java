package com.altarisnine.networkcore.api.command.argument;

import com.altarisnine.networkcore.api.util.Rank;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;

public final class RankArgument extends Argument<Rank> {
    public RankArgument(String key) {
        super(key);
    }

    @Override
    public boolean validate(String input) {
        try {
            Rank.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Rank parseArgument(String argument) throws ArgumentParseException {
        try {
            return Rank.valueOf(argument.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        return Arrays.stream(Rank.values()).map(r -> r.toString()).collect(ImmutableList.toImmutableList());
    }

    @Override
    public String getErrorMessage() {
        return "That is not a valid Rank!";
    }
}
