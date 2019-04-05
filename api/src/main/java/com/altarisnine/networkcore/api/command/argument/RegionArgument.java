package com.altarisnine.networkcore.api.command.argument;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.guard.region.Region;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class RegionArgument extends Argument<Region> {
    public RegionArgument(String key) {
        super(key);
    }

    @Override
    public Region parseArgument(String argument) throws ArgumentParseException {
        if (!Core.getApi().getGuard().regionExists(argument)) throw new ArgumentParseException(this);
        return Core.getApi().getGuard().getRegion(argument);
    }

    @Override
    public List<String> getAvailableValues() {
        return ImmutableList.copyOf(Core.getApi().getGuard().getRegionNames());
    }

    @Override
    public String getErrorMessage() {
        return "That region cannot be found!";
    }
}
