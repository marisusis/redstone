package com.altarisnine.networkcore.bukkit.server;

import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.world.World;
import com.altarisnine.networkcore.bukkit.BukkitNetworkCore;
import com.altarisnine.networkcore.bukkit.Converter;
import com.altarisnine.networkcore.common.server.CoreServer;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.UUID;

public class BukkitServer extends CoreServer {
    protected BukkitNetworkCore core;

    public BukkitServer(BukkitNetworkCore instance) {
        super(instance);
    }

    @Override
    public List<World> getWorlds() {
        return ImmutableList.copyOf(core.getWorlds().values());
    }

    @Override
    public World getWorld(String name) {
        return core.getWorld(name);
    }

    @Override
    public World getWorld(UUID uid) {
        return getWorld(Bukkit.getWorld(uid).getName());
    }

    @Override
    public Entity getEntity(UUID uuid) {
        return Converter.entity(Bukkit.getEntity(uuid));
    }

    @Override
    public int getMaxPlayers() {
        return super.getMaxPlayers();
    }

    @Override
    public void stop() {
        Bukkit.getServer().shutdown();
    }


}
