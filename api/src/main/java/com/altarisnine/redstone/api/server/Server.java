package com.altarisnine.redstone.api.server;

import com.altarisnine.redstone.api.entity.Entity;
import com.altarisnine.redstone.api.entity.living.player.OfflinePlayer;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.Guard;
import com.altarisnine.redstone.api.plugin.PluginManager;
import com.altarisnine.redstone.api.scheduling.Scheduler;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.world.World;

import java.util.List;
import java.util.UUID;

public interface Server {
    int getMaxPlayers();
    Player getPlayer(UUID uuid);
    Player getPlayer(String name);
    List<World> getWorlds();
    World getWorld(String name);
    World getWorld(UUID uid);
    World createWorld(String name);
    void stop();
    OfflinePlayer getOfflinePlayer(String name);
    OfflinePlayer getOfflinePlayer(UUID id);
    Entity getEntity(UUID uuid);
    Guard getGuard();
    PluginManager getPluginManager();
    Scheduler getScheduler();
    void announce(Text message);
}
