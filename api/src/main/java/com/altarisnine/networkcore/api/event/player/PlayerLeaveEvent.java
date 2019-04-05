package com.altarisnine.networkcore.api.event.player;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.event.HandlerList;
import lombok.Getter;

public final class PlayerLeaveEvent extends PlayerEvent {

    @Getter
    private static final HandlerList handlerList = new HandlerList();

    public PlayerLeaveEvent(Player who) {
        super(who);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
