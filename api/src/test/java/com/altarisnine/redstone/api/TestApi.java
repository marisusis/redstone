package com.altarisnine.redstone.api;

import com.altarisnine.redstone.api.configuration.ConfigurationManager;
import com.altarisnine.redstone.api.entity.living.player.OfflinePlayer;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.Guard;
import com.altarisnine.redstone.api.guard.session.Session;
import com.altarisnine.redstone.api.gui.GUIController;
import com.altarisnine.redstone.api.plugin.PluginManager;
import com.altarisnine.redstone.api.server.Server;
import com.altarisnine.redstone.api.storage.Storage;
import com.altarisnine.redstone.api.storage.database.Database;
import com.altarisnine.redstone.api.storage.sync.Sync;
import com.altarisnine.redstone.api.util.Logger;
import com.altarisnine.redstone.api.world.World;

import java.io.File;
import java.util.Set;
import java.util.UUID;

public class TestApi implements RedstoneAPI {
    @Override
    public Sync getSync() {
        return null;
    }

    @Override
    public Database getDatabase() {
        return null;
    }

    @Override
    public Storage getStorage() {
        return null;
    }

    @Override
    public Logger getLogger() {
        return new Logger() {
            @Override
            public void error(String message) {
                System.out.println("ERR: " + message);
            }

            @Override
            public void debug(String message) {
                System.out.println("DBG: " + message);
            }

            @Override
            public void info(String message) {
                System.out.println("INF: " + message);
            }
        };
    }

    @Override
    public World getWorld(String name) {
        return null;
    }

    @Override
    public PluginManager getPluginManager() {
        return null;
    }

    @Override
    public GUIController getGUIController() {
        return null;
    }

    @Override
    public Session getSession(Player player) {
        return null;
    }

    @Override
    public Session getSession(UUID uuid) {
        return null;
    }

    @Override
    public Guard getGuard() {
        return null;
    }

    @Override
    public ConfigurationManager getConfigurationManager() {
        return null;
    }

    @Override
    public File getCoreFolder() {
        return null;
    }

    @Override
    public Player getPlayer(UUID uuid) {
        return null;
    }

    @Override
    public Player getPlayer(String name) {
        return null;
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String username) {
        return null;
    }

    @Override
    public boolean isOnline(UUID uuid) {
        return false;
    }

    @Override
    public boolean isOnline(String username) {
        return false;
    }

    @Override
    public Set<String> getOnlinePlayerNames() {
        return null;
    }

    @Override
    public Set<UUID> getOnlinePlayerUniqueIds() {
        return null;
    }

    @Override
    public Server getServer() {
        return null;
    }
}
