package com.altarisnine.redstone.api.event.inventory;

import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.event.HandlerList;
import com.altarisnine.redstone.api.inventory.InventoryView;
import lombok.Getter;

public class EnchantItemEvent extends InventoryEvent {
    @Getter
    private static final HandlerList handlerList = new HandlerList();

    protected EnchantItemEvent(final Player enchanter, final InventoryView view, final Block table) {
        super(view);
        throw new UnsupportedOperationException("Not available yet!");
    }
}
