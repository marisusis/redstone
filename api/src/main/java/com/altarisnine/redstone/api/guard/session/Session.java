package com.altarisnine.redstone.api.guard.session;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.event.guard.region.RegionEnterEvent;
import com.altarisnine.redstone.api.event.guard.region.RegionExitEvent;
import com.altarisnine.redstone.api.guard.Guard;
import com.altarisnine.redstone.api.guard.flag.Flag;
import com.altarisnine.redstone.api.guard.handler.Handler;
import com.altarisnine.redstone.api.guard.region.RegionSet;
import com.altarisnine.redstone.api.guard.region.ResizableRegion;
import com.altarisnine.redstone.api.guard.spatial.vector.VectorI3;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.world.Location;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class Session {
    @Getter private final Player player;
    @Getter private Location lastValid;

    private final Guard guard;

    @Getter private final Set<Flag<?>> overrides;

    @Getter @Setter private boolean inLockdown;

    @Getter @Setter private boolean resizing;

    @Getter @Setter private VectorI3 lastResizePoint;
    @Getter @Setter private ResizableRegion lastResizeRegion;

    public Session(Guard guard, Player player) {
        this.guard = guard;
        this.player = player;
        this.lastValid = player.getLocation();
        this.overrides = new HashSet<>();
        this.inLockdown = false;
        this.resizing = false;
    }

    // TODO different types of movements
    public Location handleMoveTo(Location to) {
        // Lockdown override
        if (inLockdown) return lastValid;

        // Make sure that the player moved to a different block
        if (Location.differentBlock(lastValid, to)) {
            RegionSet toRegions = guard.getApplicableRegions(to.getWorld(), to.toVector());
            RegionSet fromRegions = guard.getApplicableRegions(lastValid.getWorld(), lastValid.toVector());

            // Check if the player can move
            for (Handler handler : Redstone.getApi().getGuard().getHandlers()) {
                if (!handler.canMoveTo(this, lastValid, to, toRegions, fromRegions)) {
                    return lastValid;
                }
            }

            RegionSet enteredRegions = new RegionSet(Sets.difference(toRegions, fromRegions));
            RegionSet exitedRegions = new RegionSet(Sets.difference(fromRegions, toRegions));

            if (enteredRegions.size() > 0) {
                // call region enter event
                guard.getServer().getEventManager().callEvent(new RegionEnterEvent(this, enteredRegions));
            }

            if (exitedRegions.size() > 0) {
                // call region enter event
                guard.getServer().getEventManager().callEvent(new RegionExitEvent(this, exitedRegions));
            }


            // Change the last valid location to the player's current location
            lastValid = to;
        }

        // Nothing happened, nothing to return
        return null;
    }

    boolean crossBoundary(Location from, Location to) {
        // Lockdown override
        if (inLockdown) return false;

        RegionSet toRegions = guard.getApplicableRegions(to.getWorld(), to.toVector());
        RegionSet fromRegions = guard.getApplicableRegions(from.getWorld(), to.toVector());

        // Check if the location is in an invalid region
        for (Handler handler : Redstone.getApi().getGuard().getHandlers()) {
            if (!handler.canMoveTo(this, from, to, fromRegions, toRegions)) {
                return false;
            }
        }

        return true;
    }

    public void toggleOverride(Flag<?> flag) {
        if (overrides.contains(flag)) overrides.remove(flag);
        else overrides.add(flag);
    }

    public boolean canBuildAt(Block target, Item with) {
        if (inLockdown) return false;

        RegionSet targetRegions = guard.getApplicableRegions(target.getLocation().getWorld(), target.getLocation().toVector());

        for (Handler handler : Redstone.getApi().getGuard().getHandlers()) {
            if (!handler.canInteract(this, Handler.Interaction.BUILD, with, target, targetRegions)) {
                return false;
            }
        }

        return true;
    }

    public void setOverride(Flag<?> flag) {
        overrides.add(flag);
    }

    public void unsetOverride(Flag<?> flag) {
        overrides.remove(flag);
    }

    public void unsetAllOverrides() {
        overrides.clear();
    }

    public boolean hasOverride(Flag<?> flag) {
        return overrides.contains(flag);
    }


}
