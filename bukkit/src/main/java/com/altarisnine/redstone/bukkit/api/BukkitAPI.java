package com.altarisnine.redstone.bukkit.api;

import com.altarisnine.redstone.api.world.World;
import com.altarisnine.redstone.bukkit.world.BukkitWorld;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.api.APIBase;
import org.bukkit.Bukkit;

public class BukkitAPI extends APIBase {

    public BukkitAPI(RedstoneCore instance) {
        super(instance);
    }

    @Override
    public World getWorld(String name) {
        return new BukkitWorld(Bukkit.getWorld(name));
    }
}