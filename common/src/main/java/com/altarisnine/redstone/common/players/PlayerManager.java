package com.altarisnine.redstone.common.players;

import com.altarisnine.redstone.api.entity.living.player.OfflinePlayer;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.storage.StorageGroup;
import com.altarisnine.redstone.api.storage.StorageHelper;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.entity.living.player.CoreOfflinePlayer;
import com.altarisnine.redstone.common.user.User;
import com.altarisnine.redstone.common.util.ServerType;
import com.mojang.authlib.GameProfile;
import lombok.Getter;
import org.apache.commons.codec.Charsets;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * The type Player manager.
 */
// TODO use manager to get player through API rather than initializing with wrapper object
public abstract class PlayerManager {

    /**
     * The core that this PlayerManager is loaded in
     */
    protected RedstoneCore core;


    /**
     * A list of the players currently loaded into Redis for this server.
     */
    @Getter protected HashMap<UUID, Player> activePlayers;
    @Getter protected HashMap<UUID, OfflinePlayer> offlinePlayers;
    @Getter protected Map<String, UUID> nameCache;


    protected PlayerManager(RedstoneCore instance) {
        core = instance;

        activePlayers = new HashMap<>();
        offlinePlayers = new HashMap<>();
        nameCache = new TreeMap<>();
    }

    public Player getPlayer(UUID uuid) {
        return activePlayers.get(uuid);
    }

    public Player getPlayer(String name) {
//       TEMP UUID uuid = core.getMojangUtils().getUniqueIdByUsername(name.toLowerCase());
        User user = core.getPlayerByName(name);

        if (user == null) return null;

        UUID uuid = user.getUniqueId();
        return getPlayer(uuid);
    }

    public void unloadPlayer(UUID uuid) {
        // Get player's active StorageGroup
        StorageGroup group = StorageHelper.getHelper(Player.class).initForDatabase(core.getSyncManager().readGroup("players", uuid.toString()));

        // Save player's info to the database
        core.getDatabaseManager().write(group);

        // Remove player from active list
        activePlayers.remove(uuid);
    }

    public void loadPlayer(UUID uuid) {
        if (activePlayers.containsKey(uuid)) throw new IllegalStateException("Player has already been loaded!");

        // Create a player provided by the active wrapper
        Player player = createPlayerInstance(uuid);

        // Add player to the list of active players
        activePlayers.put(uuid, player);

        // Get player's name
        final String name = core.getPlayer(uuid).getName();

        if (core.getType() == ServerType.PROXY) {
            StorageHelper helper = StorageHelper.getHelper(Player.class);
                // Check if player exists in MongoDB
                if (core.getDatabaseManager().containsPlayer(uuid)) {
                    // TODO loadAll should only load players who have been on recently, to use less memory
                    // Make sure the name matches the uuid
                    StorageGroup playerGroup = core.getDatabaseManager().read("players", uuid.toString());

                    // Update the username for the player's entry
                    if (!playerGroup.get("username").getValue().equalsIgnoreCase(name))
                        playerGroup.set("username", name, true);

                    // Write to storage
                    core.getSyncManager().writeGroup(playerGroup);
                    core.getDatabaseManager().write(helper.initForDatabase(playerGroup));

                } else {
                    // Create storage group for player
                    StorageGroup group = initPlayerStorage(uuid, name);

                    // Write initial values to storage
                    core.getSyncManager().writeGroup(group);
                    core.getDatabaseManager().write(group);
                }
        }
    }

    public StorageGroup initPlayerStorage(UUID uuid, String playerName) {
        // TODO check if an entry exists for the username, in the case of an offline player who hasn't joined yet

        // Get player storage helper
        StorageHelper helper = StorageHelper.getHelper(Player.class);

        // Get default values
        StorageGroup template = helper.getDefault();

        // Modify initial values
        template.set("key", uuid.toString(), true);
        template.set("username", core.getPlayer(uuid).getName(), true);

        // Set id to uuid
        template.setId(uuid.toString());

        return template;
    }

    protected abstract Player createPlayerInstance(UUID uuid);

    public final OfflinePlayer getOfflinePlayer(String username) {
        OfflinePlayer player = getPlayer(username);

        if (player == null) {
            // Get user profile by name
            GameProfile profile = core.getMojangManager().getProfileFromName(username);

            if (profile == null) {
                player = getOfflinePlayer(new GameProfile(UUID.nameUUIDFromBytes(
                        ("OfflinePlayer:" + username).getBytes(Charsets.UTF_8)
                ), username));
            } else {
                player = getOfflinePlayer(profile);
            }
        } else {
            offlinePlayers.remove(player.getUniqueId());
        }

        return player;
    }

    public final OfflinePlayer getOfflinePlayer(UUID uuid) {
        OfflinePlayer player = getPlayer(uuid);

        // If player is null, player is not online
        if (player == null) {
            // Check for existing offline player instance
            player = offlinePlayers.get(uuid);
            if (player == null) {
                player = new CoreOfflinePlayer(core, new GameProfile(uuid, null));
                offlinePlayers.put(uuid, player);
            }
        } else {
            offlinePlayers.remove(player);
        }

        return player;
    }

    private OfflinePlayer getOfflinePlayer(GameProfile profile) {
        OfflinePlayer player = new CoreOfflinePlayer(core, profile);
        offlinePlayers.put(player.getUniqueId(), player);
        return player;
    }



}
