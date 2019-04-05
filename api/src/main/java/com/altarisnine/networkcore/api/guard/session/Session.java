package com.altarisnine.networkcore.api.guard.session;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.block.Block;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.Guard;
import com.altarisnine.networkcore.api.guard.flag.Flag;
import com.altarisnine.networkcore.api.guard.handler.Handler;
import com.altarisnine.networkcore.api.guard.region.Region;
import com.altarisnine.networkcore.api.guard.region.RegionSet;
import com.altarisnine.networkcore.api.guard.region.ResizableRegion;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.world.Location;
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
            for (Handler handler : Core.getApi().getGuard().getHandlers()) {
                if (!handler.canMoveTo(this, lastValid, to, toRegions, fromRegions)) {
                    return lastValid;
                }
            }

            RegionSet newRegions = new RegionSet(Sets.difference(toRegions, fromRegions));

            if (newRegions.size() > 0) {
                StringBuilder builder = new StringBuilder();

                builder.append("&6You have entered the factions: [");

                // Send feedback to player
                for (Region newRegion : newRegions) {
                    builder.append("&b").append(newRegion.getName()).append("&6, ");
                }

                builder.append("&6]");

                player.sendActionBar(Text.of(builder.toString()));
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
        for (Handler handler : Core.getApi().getGuard().getHandlers()) {
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

        for (Handler handler : Core.getApi().getGuard().getHandlers()) {
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
