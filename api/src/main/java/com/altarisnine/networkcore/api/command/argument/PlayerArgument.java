package com.altarisnine.networkcore.api.command.argument;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public final class PlayerArgument extends Argument<Player> {

    @Getter @Setter private Player value;

    public PlayerArgument(String key) {
        super(key);
    }

    @Override
    public Player parseArgument(String argument) throws ArgumentParseException {
        Player player = Core.getApi().getPlayer(argument);
        if (player != null) {
            return player;
        } else {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        return ImmutableList.copyOf(Core.getApi().getOnlinePlayerNames());
    }

    @Override
    public String getErrorMessage() {
        return "That player cannot be found!";
    }
}
