package com.altarisnine.networkcore.api.event.inventory.interact;

import com.altarisnine.networkcore.api.event.HandlerList;
import com.altarisnine.networkcore.api.inventory.InventoryView;
import lombok.Getter;

public class InventoryDragEvent extends InventoryInteractEvent {
    @Getter
    private static final HandlerList handlerList = new HandlerList();

    protected InventoryDragEvent(InventoryView inventoryView) {
        super(inventoryView);
    }
}
