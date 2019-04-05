package com.altarisnine.redstone.bungee.command;

import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.bungee.bootstrap.BungeeCommandBootstrap;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.command.CommandManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public final class BungeeCommandManager extends CommandManager {
    public BungeeCommandManager(RedstoneCore instance) {
        super(instance);
    }

    @Override
    protected void register(CommandNode command) {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerCommand((Plugin) plugin.getBootstrap(), new BungeeCommandBootstrap(plugin, command));
        plugin.getLogger().info("Registered command " + command.getName() + '.');
    }
}
