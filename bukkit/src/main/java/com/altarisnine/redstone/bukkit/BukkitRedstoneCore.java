package com.altarisnine.redstone.bukkit;

import com.altarisnine.redstone.api.configuration.file.FileConfiguration;
import com.altarisnine.redstone.api.world.World;
import com.altarisnine.redstone.bukkit.api.BukkitAPI;
import com.altarisnine.redstone.bukkit.bootstrap.BukkitRedstoneCoreBootstrap;
import com.altarisnine.redstone.bukkit.command.BukkitCommandManager;
import com.altarisnine.redstone.bukkit.gui.BukkitGUIManager;
import com.altarisnine.redstone.bukkit.listeners.BukkitListener;
import com.altarisnine.redstone.bukkit.players.BukkitPlayerManager;
import com.altarisnine.redstone.bukkit.scheduling.BukkitNetworkScheduler;
import com.altarisnine.redstone.bukkit.server.BukkitServer;
import com.altarisnine.redstone.bukkit.user.BukkitUser;
import com.altarisnine.redstone.bukkit.world.BukkitWorld;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.api.APIBase;
import com.altarisnine.redstone.common.bootstrap.RedstoneCoreBootstrap;
import com.altarisnine.redstone.common.guard.CoreGuard;
import com.altarisnine.redstone.common.guard.SessionManager;
import com.altarisnine.redstone.common.guard.region.RegionManager;
import com.altarisnine.redstone.common.user.User;
import com.altarisnine.redstone.common.util.ServerType;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.util.*;

public class BukkitRedstoneCore extends RedstoneCore {

    private FileConfiguration regions;
    @Getter private Map<String, World> worlds = new HashMap<>();

    public BukkitRedstoneCore(RedstoneCoreBootstrap instance) {
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
    public BukkitRedstoneCoreBootstrap getBootstrap() {
        return (BukkitRedstoneCoreBootstrap) super.getBootstrap();
    }

    public World getWorld(String name) {
        if (!worlds.containsKey(name)) {
            worlds.put(name, new BukkitWorld(Bukkit.getWorld(name)));
        }


        return worlds.get(name);
    }

}
