package com.altarisnine.redstone.api.guard.handler;

import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.event.EventManager;
import com.altarisnine.redstone.api.guard.region.RegionSet;
import com.altarisnine.redstone.api.guard.session.Session;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.world.Location;
import lombok.Getter;

public abstract class Handler {

    @Getter private final EventManager eventManager;

    protected Handler(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public abstract boolean canMoveTo(Session session, Location from, Location to, RegionSet fromRegions, RegionSet toRegions);
    public abstract boolean canDamage();
    public abstract boolean canInteract(Session session, Interaction interaction, Item used, Block at, RegionSet within);

    public enum Interaction {
        PLACE,
        BREAK,
        LAVA,
        WATER,
        ANY,
        BUILD
    }
}
