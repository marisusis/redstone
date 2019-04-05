package com.altarisnine.networkcore.api.command.argument;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.entity.living.player.OfflinePlayer;

import java.util.Collections;
import java.util.List;

public class OfflinePlayerArgument extends Argument<OfflinePlayer> {
    public OfflinePlayerArgument(String key) {
        super(key);
    }

    @Override
    public OfflinePlayer parseArgument(String argument) throws ArgumentParseException {
        return Core.getApi().getOfflinePlayer(argument);
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
