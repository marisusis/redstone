package com.altarisnine.redstone.api.event.player;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.event.Event;

public abstract class PlayerEvent extends Event {
    protected final Player player;

    protected PlayerEvent(final Player who) {
        this.player = who;
    }

    public Player getPlayer() {
        return this.player;
    }
}
