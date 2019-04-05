package com.altarisnine.networkcore.sponge.players;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.players.PlayerManager;
import com.altarisnine.networkcore.sponge.players.objects.SpongePlayer;

import java.util.UUID;

public class SpongePlayerManager extends PlayerManager {

    protected SpongePlayerManager(NetworkCore instance) {
        super(instance);
    }

    @Override
    public Player createPlayerInstance(UUID uuid) {
        return new SpongePlayer(core, uuid);
    }

}
