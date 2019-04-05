package com.altarisnine.networkcore.api.event.player;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.event.HandlerList;
import com.altarisnine.networkcore.api.server.ServerInfo;

public final class PlayerSwitchServerEvent extends PlayerEvent {

    private static final HandlerList handlerList = new HandlerList();

    private final ServerInfo origin;
    private final ServerInfo destination;

    public PlayerSwitchServerEvent(Player who, ServerInfo origin, ServerInfo destination) {
        super(who);

        this.origin = origin;
        this.destination = destination;
    }

    public ServerInfo getOrigin() {
        return this.origin;
    }

    public ServerInfo getDestination() {
        return this.destination;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
