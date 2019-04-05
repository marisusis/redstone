package com.altarisnine.networkcore.bungee.players;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.bungee.entity.living.player.BungeePlayer;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.players.PlayerManager;
import net.md_5.bungee.api.ProxyServer;

import java.util.UUID;

public class BungeePlayerManager extends PlayerManager {

    public BungeePlayerManager(NetworkCore instance) {
        super(instance);
    }

    @Override
    public Player createPlayerInstance(UUID uuid) {
        return new BungeePlayer(core, ProxyServer.getInstance().getPlayer(uuid));
    }



}
