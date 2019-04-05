package com.altarisnine.networkcore.bukkit;

import com.altarisnine.networkcore.api.configuration.file.FileConfiguration;
import com.altarisnine.networkcore.api.world.World;
import com.altarisnine.networkcore.bukkit.api.BukkitAPI;
import com.altarisnine.networkcore.bukkit.bootstrap.BukkitNetworkCoreBootstrap;
import com.altarisnine.networkcore.bukkit.command.BukkitCommandManager;
import com.altarisnine.networkcore.bukkit.gui.BukkitGUIManager;
import com.altarisnine.networkcore.bukkit.listeners.BukkitListener;
import com.altarisnine.networkcore.bukkit.players.BukkitPlayerManager;
import com.altarisnine.networkcore.bukkit.scheduling.BukkitNetworkScheduler;
import com.altarisnine.networkcore.bukkit.server.BukkitServer;
import com.altarisnine.networkcore.bukkit.user.BukkitUser;
import com.altarisnine.networkcore.bukkit.world.BukkitWorld;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.api.APIBase;
import com.altarisnine.networkcore.common.bootstrap.NetworkCoreBootstrap;
import com.altarisnine.networkcore.common.guard.CoreGuard;
import com.altarisnine.networkcore.common.guard.SessionManager;
import com.altarisnine.networkcore.common.guard.region.RegionManager;
import com.altarisnine.networkcore.common.user.User;
import com.altarisnine.networkcore.common.util.ServerType;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.util.*;

public class BukkitNetworkCore extends NetworkCore {

    private FileConfiguration regions;
    @Getter private Map<String, World> worlds = new HashMap<>();

    public BukkitNetworkCore(NetworkCoreBootstrap instance) {
        super(instance);
    }

    public User getPlayer(UUID uuid) {
        Player player = getBootstrap().getServer().getPlayer(uuid);
        return (player != null) ? new BukkitUser(player) : null;
    }

    public User getPlayerByName(String name) {
        Player player = getBootstrap().getServer().getPlayer(name);
        return (player != null) ? new BukkitUser(player) : null;
    }

    public List<User> getOnlineUsers() {
        List<User> users = new ArrayList<>();
        for (Player player : getBootstrap().getServer().getOnlinePlayers()) {
            users.add(new BukkitUser(player));
        }
        return users;
    }

    public void broadcast(String message) {
        getBootstrap().getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void registerListeners() {
        PluginManager pluginManager = getBootstrap().getServer().getPluginManager();
        pluginManager.registerEvents(new BukkitListener(this), getBootstrap());
    }

    @Override
    protected void preLoad() {
        super.preLoad();
        networkScheduler = new BukkitNetworkScheduler(this);
        guiManager = new BukkitGUIManager(this);
        commandManager = new BukkitCommandManager(this);
        guard = new CoreGuard(this);
        regionManager = new RegionManager(this);
        sessionManager = new SessionManager(this);
        server = new BukkitServer(this);

    }

    @Override
    protected void load() {
        super.load();
        playerManager = new BukkitPlayerManager(this);
    }

    @Override
    protected void postLoad() {
        super.postLoad();

        regionManager.loadAll();

    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();

    }

    @Override
    public void log(String message) {
        getBootstrap().getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public ServerType getType() {
        return ServerType.INSTANCE;
    }

    @Override
    public File getDataFolder() {
        return getBootstrap().getDataFolder();
    }

    @Override
    public APIBase getAPIImpl() {
        return new BukkitAPI(this);
    }

    @Override
    public BukkitNetworkCoreBootstrap getBootstrap() {
        return (BukkitNetworkCoreBootstrap) super.getBootstrap();
    }

    public World getWorld(String name) {
        if (!worlds.containsKey(name)) {
            worlds.put(name, new BukkitWorld(Bukkit.getWorld(name)));
        }


        return worlds.get(name);
    }

}
