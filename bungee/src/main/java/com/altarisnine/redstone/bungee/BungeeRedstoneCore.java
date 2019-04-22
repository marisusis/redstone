package com.altarisnine.redstone.bungee;

import com.altarisnine.redstone.bungee.api.BungeeAPI;
import com.altarisnine.redstone.bungee.bootstrap.BungeeRedstoneCoreBootstrap;
import com.altarisnine.redstone.bungee.command.BungeeCommandManager;
import com.altarisnine.redstone.bungee.commands.LeaveCommand;
import com.altarisnine.redstone.bungee.listeners.BungeeListener;
import com.altarisnine.redstone.bungee.players.BungeePlayerManager;
import com.altarisnine.redstone.bungee.scheduling.BungeeNetworkScheduler;
import com.altarisnine.redstone.bungee.user.BungeeUser;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.api.APIBase;
import com.altarisnine.redstone.common.bootstrap.RedstoneCoreBootstrap;
import com.altarisnine.redstone.common.user.User;
import com.altarisnine.redstone.common.util.ServerType;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.PluginManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BungeeRedstoneCore extends RedstoneCore {

    public BungeeRedstoneCore(RedstoneCoreBootstrap instance) {
        super(instance);
    }

    @Override
    public User getPlayer(UUID uuid) {
        ProxiedPlayer player = getBootstrap().getProxy().getPlayer(uuid);
        return (player != null) ? new BungeeUser(player) : null;
    }

    @Override
    public User getPlayerByName(String name) {
        ProxiedPlayer player = getBootstrap().getProxy().getPlayer(name);
        return (player != null) ? new BungeeUser(player) : null;
    }

    @Override
    public List<User> getOnlineUsers() {
        List<User> users = new ArrayList<>();
        for (ProxiedPlayer player : getBootstrap().getProxy().getPlayers()) {
            users.add(new BungeeUser(player));
        }
        return users;
    }

    @Override
    public void broadcast(String message) {
        getBootstrap().getProxy().broadcast(TextComponent.fromLegacyText(
                ChatColor.translateAlternateColorCodes('&', message)));
    }

    @Override
    protected void preLoad() {
        super.preLoad();


        networkScheduler = new BungeeNetworkScheduler(this);
        commandManager = new BungeeCommandManager(this);
    }

    @Override
    protected void load() {
        super.load();
        playerManager = new BungeePlayerManager(this);
    }

    @Override
    protected void postLoad() {
        super.postLoad();
        commandManager.registerCommand(new LeaveCommand(), this);
    }

    @Override
    public void registerListeners() {
        PluginManager pluginManager = getBootstrap().getProxy().getPluginManager();
        pluginManager.registerListener(getBootstrap(), new BungeeListener(this));
        getLogger().info("Registered listeners.");
    }

    @Override
    public void log(String message) {
        getBootstrap().getProxy().getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public ServerType getType() {
        return ServerType.PROXY;
    }

    @Override
    public File getDataFolder() {
        return getBootstrap().getDataFolder();
    }

    @Override
    public APIBase getAPIImpl() {
        return new BungeeAPI(this);
    }

    @Override
    public BungeeRedstoneCoreBootstrap getBootstrap() {
        return (BungeeRedstoneCoreBootstrap) super.getBootstrap();
    }

}
