package com.altarisnine.networkcore.api.server;

import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.entity.living.player.OfflinePlayer;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.Guard;
import com.altarisnine.networkcore.api.plugin.PluginManager;
import com.altarisnine.networkcore.api.scheduling.Scheduler;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.world.World;

import java.util.List;
import java.util.UUID;

public interface Server {
    int getMaxPlayers();
    Player getPlayer(UUID uuid);
    Player getPlayer(String name);
    List<World> getWorlds();
    World getWorld(String name);
    World getWorld(UUID uid);
    void stop();
    OfflinePlayer getOfflinePlayer(String name);
    OfflinePlayer getOfflinePlayer(UUID id);
    Entity getEntity(UUID uuid);
    Guard getGuard();
    PluginManager getPluginManager();
    Scheduler getScheduler();
    void announce(Text message);
}
