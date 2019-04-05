package com.altarisnine.redstone.api.guard.handler;

import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.event.EventManager;
import com.altarisnine.redstone.api.guard.flag.Flags;
import com.altarisnine.redstone.api.guard.region.RegionSet;
import com.altarisnine.redstone.api.guard.session.Session;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.world.Location;

public class EntryHandler extends Handler {
    public EntryHandler(EventManager eventManager) {
        super(eventManager);
    }

    @Override
    public boolean canMoveTo(Session session, Location from, Location to, RegionSet fromRegions, RegionSet toRegions) { // FIXME user cannot move when already inside region
        if (!toRegions.testState(session, Flags.ENTRY) && !session.hasOverride(Flags.ENTRY)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canDamage() {
        return true;
    }

    @Override
    public boolean canInteract(Session session, Interaction interaction, Item used, Block at, RegionSet within) {
        return true;
    }
}
