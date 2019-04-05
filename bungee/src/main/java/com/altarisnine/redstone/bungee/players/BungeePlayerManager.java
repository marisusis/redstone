package com.altarisnine.redstone.bungee.players;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.bungee.entity.living.player.BungeePlayer;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.players.PlayerManager;
import net.md_5.bungee.api.ProxyServer;

import java.util.UUID;

public class BungeePlayerManager extends PlayerManager {

    public BungeePlayerManager(RedstoneCore instance) {
        super(instance);
    }

    @Override
    public Player createPlayerInstance(UUID uuid) {
        return new BungeePlayer(core, ProxyServer.getInstance().getPlayer(uuid));
    }



}
