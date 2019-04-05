package com.altarisnine.networkcore.bukkit.inventory;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.inventory.Inventory;
import com.altarisnine.networkcore.api.inventory.InventoryView;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.bukkit.Converter;
import com.altarisnine.networkcore.common.NetworkCore;

public class BukkitInventoryView implements InventoryView {
    private final org.bukkit.inventory.InventoryView view;
    private final NetworkCore core;

    public BukkitInventoryView(NetworkCore instance, org.bukkit.inventory.InventoryView view) {
        this.core = instance;
        this.view = view;
    }

    @Override
    public Inventory getTopInventory() {
        return new BukkitInventory(core, view.getTopInventory());
    }

    @Override
    public Inventory getBottomInventory() {
        return new BukkitInventory(core, view.getBottomInventory());
    }

    @Override
    public Player getPlayer() {
        return Core.getApi().getPlayer(view.getPlayer().getUniqueId());
    }

    @Override
    public void setItem(int slot, Item item) {
        view.setItem(slot, Converter.item(item));
    }

    @Override
    public Item getItem(int slot) {
        return Converter.item(view.getItem(slot));
    }

    @Override
    public void setItemOnCursor(Item item) {
        throw new UnsupportedOperationException("not implemented!");
    }

    @Override
    public Item getItemOnCursor() {
        throw new UnsupportedOperationException("not implemented!");
    }

    @Override
    public void close() {
        view.getPlayer().closeInventory();
    }

    @Override
    public int slots() {
        return view.countSlots();
    }

    @Override
    public Text getTitle() {
        return Text.of(view.getTitle());
    }
}
