package com.altarisnine.networkcore.api.event.player;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.event.Cancellable;
import com.altarisnine.networkcore.api.event.HandlerList;
import com.altarisnine.networkcore.api.world.Location;
import lombok.Getter;
import lombok.Setter;

public class PlayerMoveEvent extends PlayerEvent implements Cancellable {
    @Getter private static final HandlerList handlerList = new HandlerList();

    @Getter @Setter private boolean cancelled;
    // TODO do this in a better way
    @Getter @Setter private Location to;
    @Getter @Setter private boolean shouldChange;

    public PlayerMoveEvent(final Player who, final Location to) {
        super(who);
        cancelled = false;
        this.to = to;
        shouldChange = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
