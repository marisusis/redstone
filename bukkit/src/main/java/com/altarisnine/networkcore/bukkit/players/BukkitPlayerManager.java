package com.altarisnine.networkcore.bukkit.players;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.bukkit.entity.living.player.BukkitPlayer;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.players.PlayerManager;

import java.util.UUID;

public class BukkitPlayerManager extends PlayerManager {

    public BukkitPlayerManager(NetworkCore instance) {
        super(instance);
    }

    @Override
    public Player createPlayerInstance(UUID uuid) {
        return new BukkitPlayer(core, uuid);
    }

}
