package com.altarisnine.redstone.common.api;

import com.altarisnine.redstone.api.RedstoneAPI;
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
import com.altarisnine.redstone.common.RedstoneCore;

import java.io.File;
import java.util.Set;
import java.util.UUID;

public abstract class APIBase implements RedstoneAPI {
    private RedstoneCore core;

    protected APIBase(RedstoneCore instance) {
        core = instance;
    }

    @Override
    public Player getPlayer(UUID uuid) {
        return core.getPlayerManager().getPlayer(uuid);
    }

    @Override
    public Player getPlayer(String name) {
        return core.getPlayerManager().getPlayer(name);
    }


    @Override
    public OfflinePlayer getOfflinePlayer(String username) {
        return core.getPlayerManager().getOfflinePlayer(username);
    }

    @Override
    public boolean isOnline(UUID uuid) {
        return core.getPlayerManager().getActivePlayers().containsKey(uuid);
    }

    @Override
    public boolean isOnline(String username) {
        return false; // TODO implementation
    }

    @Override
    public Sync getSync() {
        return core.getSyncManager();
    }

    @Override
    public Logger getLogger() {
        return core.getLogger();
    }

    @Override
    public Database getDatabase() {
        return core.getDatabaseManager();
    }

    @Override
    public Storage getStorage() {
        return core.getStorageManager();
    }

    @Override
    public PluginManager getPluginManager() {
        return core.getPluginManager();
    }

    @Override
    public GUIController getGUIController() {
        return core.getGuiManager();
    }

    @Override
    public Guard getGuard() {
        return core.getGuard();
    }

    @Override
    public Set<String> getOnlinePlayerNames() {
        return core.getOnlinePlayerNames();
    }

    @Override
    public Set<UUID> getOnlinePlayerUniqueIds() {
        return core.getOnlinePlayerUniqueIds();
    }

    @Override
    public final Server getServer() {
        return core.getServer();
    }

    @Override
    public Session getSession(Player player) {
        return getSession(player.getUniqueId());
    }

    @Override
    public ConfigurationManager getConfigurationManager() {
        return core.getConfigurationManager();
    }

    @Override
    public File getCoreFolder() {
        return core.getDataFolder();
    }

    @Override
    public Session getSession(UUID uuid) {
        return core.getSessionManager().getSession(uuid);
    }
}
