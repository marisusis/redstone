package com.altarisnine.networkcore.api.event.inventory;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.event.Event;
import com.altarisnine.networkcore.api.event.HandlerList;
import com.altarisnine.networkcore.api.inventory.Inventory;
import com.altarisnine.networkcore.api.inventory.InventoryView;
import lombok.Getter;

import java.util.List;

public abstract class InventoryEvent extends Event {
    @Getter private static final HandlerList handlerList = new HandlerList();

    @Getter protected InventoryView view;

    protected InventoryEvent(InventoryView view) {
        this.view = view;
    }

    public List<Player> getViewers() {
        return this.view.getTopInventory().getViewers();
    }

    public Inventory getInventory() {
        return this.view.getTopInventory();
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
