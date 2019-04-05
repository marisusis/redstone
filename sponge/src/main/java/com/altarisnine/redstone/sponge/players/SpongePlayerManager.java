package com.altarisnine.redstone.sponge.players;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.players.PlayerManager;
import com.altarisnine.redstone.sponge.players.objects.SpongePlayer;

import java.util.UUID;

public class SpongePlayerManager extends PlayerManager {

    protected SpongePlayerManager(RedstoneCore instance) {
        super(instance);
    }

    @Override
    public Player createPlayerInstance(UUID uuid) {
        return new SpongePlayer(core, uuid);
    }

}
