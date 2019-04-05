package com.altarisnine.networkcore.api.event.inventory;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.event.Cancellable;
import com.altarisnine.networkcore.api.event.HandlerList;
import com.altarisnine.networkcore.api.inventory.InventoryView;
import lombok.Getter;
import lombok.Setter;

public class InventoryOpenEvent extends InventoryEvent implements Cancellable {
    @Getter
    private static final HandlerList handlerList = new HandlerList();

    @Getter @Setter private boolean cancelled;

    public InventoryOpenEvent(InventoryView view) {
        super(view);
        cancelled = false;
    }

    public final Player getPlayer() {
        return view.getPlayer();
    }

}
