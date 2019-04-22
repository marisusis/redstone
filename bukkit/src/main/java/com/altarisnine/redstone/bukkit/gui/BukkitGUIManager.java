package com.altarisnine.redstone.bukkit.gui;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.gui.GUI;
import com.altarisnine.redstone.bukkit.Converter;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.gui.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class BukkitGUIManager extends GUIManager {
    public BukkitGUIManager(RedstoneCore instance) {
        super(instance);
    }

    @Override
    protected void openGUIInventory(Player player, GUI gui) {
        Inventory inventory = Bukkit.createInventory(Bukkit.getPlayer(player.getUniqueId()), gui.getSize(), gui.getTitle().toFormat().replace("&", "§"));

        gui.getItems().forEach((index, item) -> inventory.setItem(index, Converter.item(item)));

        Bukkit.getPlayer(player.getUniqueId()).openInventory(inventory);
    }
}
