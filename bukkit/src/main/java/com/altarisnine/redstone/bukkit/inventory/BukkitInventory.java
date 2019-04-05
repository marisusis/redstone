package com.altarisnine.redstone.bukkit.inventory;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.inventory.Inventory;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.bukkit.Converter;
import com.altarisnine.redstone.common.RedstoneCore;
import org.bukkit.entity.HumanEntity;

import java.util.ArrayList;
import java.util.List;

public class BukkitInventory implements Inventory {

    private final org.bukkit.inventory.Inventory inventory;
    protected final RedstoneCore core;

    public BukkitInventory(RedstoneCore instance, org.bukkit.inventory.Inventory inventory) {
        this.inventory = inventory;
        this.core = instance;
    }

    @Override
    public int getSize() {
        return inventory.getSize();
    }

    @Override
    public void setItem(int slot, Item item) {
        inventory.setItem(slot, Converter.item(item));
    }

    @Override
    public Item getItem(int slot) {
        return Converter.item(inventory.getItem(slot));
    }

    @Override
    public List<Player> getViewers() {
        List<Player> players = new ArrayList<>();

        for (HumanEntity humanEntity : inventory.getViewers()) {
            if (humanEntity instanceof org.bukkit.entity.Player) {
                players.add(core.getPlayerManager().getPlayer(humanEntity.getUniqueId()));
            }
        }

        return players;
    }

    @Override
    public void addItem(Item item) {
        inventory.addItem(Converter.item(item));
    }


}
