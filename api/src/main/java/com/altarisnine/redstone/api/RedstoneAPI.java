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

/**
 * The interface Redstone api.
 */
public interface RedstoneAPI {



    /**
     * Gets the current Sync
     *
     * @return the sync
     */
    Sync getSync();

    /**
     * Gets the current Database
     *
     * @return the database
     */
    Database getDatabase();

    Storage getStorage();

    /**
     * Gets logger.
     *
     * @return the logger
     */
    Logger getLogger();

    /**
     * Gets a world by its name.
     *
     * @param name the name
     * @return the world
     */
    World getWorld(String name);

    /**
     * Gets plugin manager.
     *
     * @return the plugin manager
     */
    PluginManager getPluginManager();

    /**
     * Gets gui controller.
     *
     * @return the gui controller
     */
    GUIController getGUIController();

    /**
     * Gets session.
     *
     * @param player the player
     * @return the session
     */
    Session getSession(Player player);

    /**
     * Gets session.
     *
     * @param uuid the uuid
     * @return the session
     */
    Session getSession(UUID uuid);

    /**
     * Gets guard.
     *
     * @return the guard
     */
    Guard getGuard();

    ConfigurationManager getConfigurationManager();

    File getCoreFolder();

    /* PLAYERS */

    /**
     * Gets a player by their UUID
     *
     * @param uuid the uuid
     * @return the player
     */
    Player getPlayer(UUID uuid);

    /**
     * Gets player by their name
     *
     * @param name the name
     * @return the player
     */
    Player getPlayer(String name);

    OfflinePlayer getOfflinePlayer(String username);

    boolean isOnline(UUID uuid);
    boolean isOnline(String username);

    Set<String> getOnlinePlayerNames();
    Set<UUID> getOnlinePlayerUniqueIds();

    Server getServer();

}
