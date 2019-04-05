package com.altarisnine.redstone.common.guard;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.session.Session;
import com.altarisnine.redstone.common.RedstoneCore;

import java.util.HashMap;
import java.util.UUID;

public class SessionManager {
    private RedstoneCore plugin;
    private HashMap<UUID, Session> sessions;

    public SessionManager(RedstoneCore instance) {
        this.plugin = instance;
        this.sessions = new HashMap<>();
    }

    public void createSession(Player player) {
        // Make sure there isn't already a loaded session
        if (!sessions.containsKey(player.getUniqueId())) {
            // Create and add new Session
            sessions.put(player.getUniqueId(), new Session(plugin.getGuard(), player));
        }
    }

    public void unloadSession(UUID uuid) {
        // Remove session from the list
        sessions.remove(uuid);
    }

    public Session getSession(UUID uuid) {
        return sessions.get(uuid);
    }

}
