package com.altarisnine.networkcore.bungee.bootstrap;

import com.altarisnine.networkcore.bungee.BungeeNetworkCore;
import com.altarisnine.networkcore.common.bootstrap.NetworkCoreBootstrap;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeNetworkCoreBootstrap extends Plugin implements NetworkCoreBootstrap {
    private BungeeNetworkCore plugin;

    public BungeeNetworkCoreBootstrap() {
        this.plugin = new BungeeNetworkCore(this);
    }

    @Override
    public NetworkCoreBootstrap getBootstrap() {
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
