package com.altarisnine.redstone.api.guard.handler;

import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.event.EventManager;
import com.altarisnine.redstone.api.guard.flag.Flags;
import com.altarisnine.redstone.api.guard.region.RegionSet;
import com.altarisnine.redstone.api.guard.session.Session;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.world.Location;

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
