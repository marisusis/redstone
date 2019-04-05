package com.altarisnine.networkcore.bukkit.api;

import com.altarisnine.networkcore.api.world.World;
import com.altarisnine.networkcore.bukkit.world.BukkitWorld;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.api.APIBase;
import org.bukkit.Bukkit;

public class BukkitAPI extends APIBase {

    public BukkitAPI(NetworkCore instance) {
        super(instance);
    }

    @Override
    public World getWorld(String name) {
        return new BukkitWorld(Bukkit.getWorld(name));
    }
}