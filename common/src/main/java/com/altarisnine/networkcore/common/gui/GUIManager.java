package com.altarisnine.networkcore.common.gui;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.gui.GUI;
import com.altarisnine.networkcore.api.gui.GUIController;
import com.altarisnine.networkcore.common.NetworkCore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class GUIManager implements GUIController {
    private NetworkCore plugin;

    private final Map<UUID, GUI> openGUI;

    public GUIManager(NetworkCore instance) {
        this.plugin = instance;
        this.openGUI = new HashMap<>();
    }

    public final void openGUI(Player player, GUI gui) {
        // Check to make sure that the player doesn't have a GUI open currently
        if (!openGUI.containsKey(player.getUniqueId())) {
            openGUI.put(player.getUniqueId(), gui);
            openGUIInventory(player, gui);
        }
    }

    public final void closeGUI(Player player) {
        if (openGUI.containsKey(player.getUniqueId())) {
            openGUI.remove(player.getUniqueId());
        } else throw new IllegalStateException("That player does not have a GUI open!");
    }

    public final boolean hasOpenGUI(Player player) {
        return openGUI.containsKey(player.getUniqueId());
    }

    public final GUI getOpenGUI(Player player) {
        return openGUI.get(player.getUniqueId());
    }

    protected abstract void openGUIInventory(Player player, GUI gui);
}
