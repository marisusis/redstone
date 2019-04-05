package com.altarisnine.redstone.api.command.argument;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.guard.region.Region;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class RegionArgument extends Argument<Region> {
    public RegionArgument(String key) {
        super(key);
    }

    @Override
    public Region parseArgument(String argument) throws ArgumentParseException {
        if (!Redstone.getApi().getGuard().regionExists(argument)) throw new ArgumentParseException(this);
        return Redstone.getApi().getGuard().getRegion(argument);
    }

    @Override
    public List<String> getAvailableValues() {
        return ImmutableList.copyOf(Redstone.getApi().getGuard().getRegionNames());
    }

    @Override
    public String getErrorMessage() {
        return "That region cannot be found!";
    }
}
