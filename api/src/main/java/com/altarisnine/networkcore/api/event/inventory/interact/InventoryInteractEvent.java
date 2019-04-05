package com.altarisnine.networkcore.api.event.inventory.interact;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.event.Cancellable;
import com.altarisnine.networkcore.api.event.HandlerList;
import com.altarisnine.networkcore.api.event.inventory.InventoryEvent;
import com.altarisnine.networkcore.api.inventory.InventoryView;
import lombok.Getter;
import lombok.Setter;

public abstract class InventoryInteractEvent extends InventoryEvent implements Cancellable {
    @Getter private static final HandlerList handlerList = new HandlerList();

    @Getter @Setter private boolean cancelled;


    protected InventoryInteractEvent(InventoryView inventoryView) {
        super(inventoryView);
    }

    public Player getClicker() {
        return getView().getPlayer();
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
