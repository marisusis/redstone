package com.altarisnine.redstone.bukkit.bootstrap;

import com.altarisnine.redstone.bukkit.BukkitRedstoneCore;
import com.altarisnine.redstone.common.bootstrap.RedstoneCoreBootstrap;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitRedstoneCoreBootstrap extends JavaPlugin implements RedstoneCoreBootstrap {
    private BukkitRedstoneCore plugin;

    public BukkitRedstoneCoreBootstrap() {
        plugin = new BukkitRedstoneCore(this);
    }

    public RedstoneCoreBootstrap getBootstrap() {
        return this;
    }

    @Override
    public void onDisable() {
        plugin.disable();
    }

    @Override
    public void onEnable() {
        plugin.enable();
    }
}
