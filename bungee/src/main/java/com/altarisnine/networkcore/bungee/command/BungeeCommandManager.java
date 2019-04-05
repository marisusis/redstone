package com.altarisnine.networkcore.bungee.command;

import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.bungee.bootstrap.BungeeCommandBootstrap;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.command.CommandManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public final class BungeeCommandManager extends CommandManager {
    public BungeeCommandManager(NetworkCore instance) {
        super(instance);
    }

    @Override
    protected void register(CommandNode command) {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerCommand((Plugin) plugin.getBootstrap(), new BungeeCommandBootstrap(plugin, command));
        plugin.getLogger().info("Registered command " + command.getName() + '.');
    }
}
