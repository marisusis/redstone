package com.altarisnine.networkcore.api.guard.handler;

import com.altarisnine.networkcore.api.block.Block;
import com.altarisnine.networkcore.api.event.EventManager;
import com.altarisnine.networkcore.api.guard.flag.Flags;
import com.altarisnine.networkcore.api.guard.region.RegionSet;
import com.altarisnine.networkcore.api.guard.session.Session;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.world.Location;

public class BuildHandler extends Handler {
    public BuildHandler(EventManager eventManager) {
        super(eventManager);
    }

    @Override
    public boolean canMoveTo(Session session, Location from, Location to, RegionSet fromRegions, RegionSet toRegions) {
        return true;
    }

    @Override
    public boolean canDamage() {
        return true;
    }

    @Override
    public boolean canInteract(Session session, Interaction interaction, Item used, Block at, RegionSet within) {
        if (!interaction.equals(Interaction.BUILD)) return true;

        // For now, all interactions are controlled by the BUILD flag
        if (!within.testState(session, Flags.BUILD) && !session.hasOverride(Flags.BUILD)) {
            return false;
        }

        return true;
    }
}
