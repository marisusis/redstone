package com.altarisnine.redstone.api.event.inventory.interact;

import com.altarisnine.redstone.api.event.Cancellable;
import com.altarisnine.redstone.api.event.HandlerList;
import com.altarisnine.redstone.api.event.inventory.InventoryEvent;
import com.altarisnine.redstone.api.inventory.Inventory;
import com.altarisnine.redstone.api.inventory.InventoryView;
import com.altarisnine.redstone.api.inventory.Item;
import lombok.Getter;
import lombok.Setter;

public class InventoryClickEvent extends InventoryEvent implements Cancellable {
    @Getter private static final HandlerList handlerList = new HandlerList();
    @Getter private final int slot;
    @Getter private final Inventory clickedInventory;
    @Getter @Setter private boolean cancelled;

    public InventoryClickEvent(InventoryView view, int slot) {
        super(view);
        this.slot = slot;
        if (slot < 0) {
            clickedInventory = null;
        } else if (view.getTopInventory() != null && slot < view.getTopInventory().getSize()) {
            this.clickedInventory = view.getTopInventory();
        } else {
            this.clickedInventory = view.getBottomInventory();
        }

        // TODO convert slot number to local slot
    }

    public Item getActiveItem() {
        // TODO implement different slot types
        return view.getItem(slot);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
