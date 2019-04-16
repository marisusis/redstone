package com.altarisnine.redstone.bukkit.server;

import com.altarisnine.redstone.api.entity.Entity;
import com.altarisnine.redstone.api.world.World;
import com.altarisnine.redstone.bukkit.BukkitRedstoneCore;
import com.altarisnine.redstone.bukkit.Converter;
import com.altarisnine.redstone.common.server.CoreServer;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BukkitServer extends CoreServer {
    protected BukkitRedstoneCore core;

    public BukkitServer(BukkitRedstoneCore instance) {
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
        return getWorld(Objects.requireNonNull(Bukkit.getWorld(uid)).getName());
    }

    @Override
    public World createWorld(String name) {
        // Create the world
        return getWorld(Objects.requireNonNull(Bukkit.createWorld(new WorldCreator("name"))).getName());
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
