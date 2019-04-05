package com.altarisnine.redstone.bukkit.players;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.bukkit.entity.living.player.BukkitPlayer;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.players.PlayerManager;

import java.util.UUID;

public class BukkitPlayerManager extends PlayerManager {

    public BukkitPlayerManager(RedstoneCore instance) {
        super(instance);
    }

    @Override
    public Player createPlayerInstance(UUID uuid) {
        return new BukkitPlayer(core, uuid);
    }

}
