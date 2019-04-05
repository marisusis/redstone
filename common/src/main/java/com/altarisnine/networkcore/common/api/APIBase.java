package com.altarisnine.networkcore.common.api;

import com.altarisnine.networkcore.api.CoreAPI;
import com.altarisnine.networkcore.api.configuration.ConfigurationManager;
import com.altarisnine.networkcore.api.entity.living.player.OfflinePlayer;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.Guard;
import com.altarisnine.networkcore.api.guard.session.Session;
import com.altarisnine.networkcore.api.gui.GUIController;
import com.altarisnine.networkcore.api.plugin.PluginManager;
import com.altarisnine.networkcore.api.server.Server;
import com.altarisnine.networkcore.api.storage.Storage;
import com.altarisnine.networkcore.api.storage.database.Database;
import com.altarisnine.networkcore.api.storage.sync.Sync;
import com.altarisnine.networkcore.api.util.Logger;
import com.altarisnine.networkcore.common.NetworkCore;

import java.io.File;
import java.util.Set;
import java.util.UUID;

public abstract class APIBase implements CoreAPI {
    private NetworkCore core;

    protected APIBase(NetworkCore instance) {
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
