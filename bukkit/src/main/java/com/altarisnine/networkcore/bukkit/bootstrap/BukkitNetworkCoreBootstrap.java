package com.altarisnine.networkcore.bukkit.bootstrap;

import com.altarisnine.networkcore.bukkit.BukkitNetworkCore;
import com.altarisnine.networkcore.common.bootstrap.NetworkCoreBootstrap;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitNetworkCoreBootstrap extends JavaPlugin implements NetworkCoreBootstrap {
    private BukkitNetworkCore plugin;

    public BukkitNetworkCoreBootstrap() {
        plugin = new BukkitNetworkCore(this);
    }

    public NetworkCoreBootstrap getBootstrap() {
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
