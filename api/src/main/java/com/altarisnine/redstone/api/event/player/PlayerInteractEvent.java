package com.altarisnine.redstone.api.event.player;

import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.event.Cancellable;
import com.altarisnine.redstone.api.event.HandlerList;
import com.altarisnine.redstone.api.event.block.Action;
import lombok.Getter;
import lombok.Setter;

public class PlayerInteractEvent extends PlayerEvent implements Cancellable {

    @Getter private static final HandlerList handlerList = new HandlerList();

    @Getter private final Action action;
    @Getter private final Block block;
    @Getter @Setter private boolean cancelled;

    public PlayerInteractEvent(final Player who, final Action action, final Block block) {
        super(who);

        this.action = action;
        this.block = block;
        this.cancelled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
