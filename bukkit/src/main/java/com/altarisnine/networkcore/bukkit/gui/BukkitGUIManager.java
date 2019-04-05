package com.altarisnine.networkcore.bukkit.gui;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.gui.GUI;
import com.altarisnine.networkcore.bukkit.Converter;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.gui.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class BukkitGUIManager extends GUIManager {
    public BukkitGUIManager(NetworkCore instance) {
        super(instance);
    }

    @Override
    protected void openGUIInventory(Player player, GUI gui) {
        Inventory inventory = Bukkit.createInventory(Bukkit.getPlayer(player.getUniqueId()), gui.getSize());

        gui.getItems().forEach((index, item) -> inventory.setItem(index, Converter.item(item)));

        Bukkit.getPlayer(player.getUniqueId()).openInventory(inventory);
    }
}
