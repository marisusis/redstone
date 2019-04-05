package com.altarisnine.networkcore.api.guard.handler;

import com.altarisnine.networkcore.api.block.Block;
import com.altarisnine.networkcore.api.event.EventManager;
import com.altarisnine.networkcore.api.guard.flag.Flags;
import com.altarisnine.networkcore.api.guard.region.RegionSet;
import com.altarisnine.networkcore.api.guard.session.Session;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.world.Location;

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
