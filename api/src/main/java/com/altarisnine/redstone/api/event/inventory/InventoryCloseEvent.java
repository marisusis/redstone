package com.altarisnine.redstone.api.event.inventory;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.event.HandlerList;
import com.altarisnine.redstone.api.inventory.InventoryView;
import lombok.Getter;

public class InventoryCloseEvent extends InventoryEvent {
    @Getter private static final HandlerList handlerList = new HandlerList();

    public InventoryCloseEvent(InventoryView inventoryView) {
        super(inventoryView);
    }

    public final Player getPlayer() {
        return view.getPlayer();
    }
}
