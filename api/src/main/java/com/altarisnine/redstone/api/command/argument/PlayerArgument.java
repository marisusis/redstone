package com.altarisnine.redstone.api.command.argument;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.entity.living.player.Player;
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
        Player player = Redstone.getApi().getPlayer(argument);
        if (player != null) {
            return player;
        } else {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        return ImmutableList.copyOf(Redstone.getApi().getOnlinePlayerNames());
    }

    @Override
    public String getErrorMessage() {
        return "That player cannot be found!";
    }
}
