package com.altarisnine.redstone.api.command.argument;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.entity.living.player.OfflinePlayer;

import java.util.Collections;
import java.util.List;

public class OfflinePlayerArgument extends Argument<OfflinePlayer> {
    public OfflinePlayerArgument(String key) {
        super(key);
    }

    @Override
    public OfflinePlayer parseArgument(String argument) throws ArgumentParseException {
        return Redstone.getApi().getOfflinePlayer(argument);
    }

    @Override
    public List<String> getAvailableValues() {
         return Collections.emptyList();
    }

    @Override
    public boolean validate(String input) {
        return true;
    }

    @Override
    public String getErrorMessage() {
        return "This should really never happen. You can always create an OfflinePlayer.";
    }
}
