package com.altarisnine.redstone.bungee.bootstrap;

import com.altarisnine.redstone.bungee.BungeeRedstoneCore;
import com.altarisnine.redstone.common.bootstrap.RedstoneCoreBootstrap;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeRedstoneCoreBootstrap extends Plugin implements RedstoneCoreBootstrap {
    private BungeeRedstoneCore plugin;

    public BungeeRedstoneCoreBootstrap() {
        this.plugin = new BungeeRedstoneCore(this);
    }

    @Override
    public RedstoneCoreBootstrap getBootstrap() {
        return this;
    }

    @Override
    public void onEnable() {
        plugin.enable();
    }

    @Override
    public void onDisable() {
        plugin.disable();
    }
}
