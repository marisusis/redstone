package com.altarisnine.networkcore.api.inventory;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import lombok.Getter;

// TODO convert to interface and create bukkit implementation
@Deprecated
public class InventoryViewImpl {

    @Getter private final Inventory topInventory;
    @Getter private final Inventory bottomInventory;
    @Getter private final Player viewer;

    public InventoryViewImpl(Player viewer, Inventory topInventory, Inventory bottomInventory) {
        this.viewer = viewer;
        this.topInventory = topInventory;
        this.bottomInventory = bottomInventory;
    }

    public Item getItem(int slot) {
        return (slot < this.topInventory.getSize()) ? this.topInventory.getItem(slot) : this.bottomInventory.getItem(slot - this.topInventory.getSize());
    }

}
