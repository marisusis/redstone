package com.altarisnine.redstone.common.guard;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.Guard;
import com.altarisnine.redstone.api.guard.flag.Flag;
import com.altarisnine.redstone.api.guard.flag.FlagNotFoundException;
import com.altarisnine.redstone.api.guard.flag.Flags;
import com.altarisnine.redstone.api.guard.handler.BuildHandler;
import com.altarisnine.redstone.api.guard.handler.EntryHandler;
import com.altarisnine.redstone.api.guard.handler.ExitHandler;
import com.altarisnine.redstone.api.guard.handler.Handler;
import com.altarisnine.redstone.api.guard.region.Region;
import com.altarisnine.redstone.api.guard.region.RegionSet;
import com.altarisnine.redstone.api.guard.session.Session;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Vector;
import com.altarisnine.redstone.api.world.Location;
import com.altarisnine.redstone.api.world.World;
import com.altarisnine.redstone.common.RedstoneCore;
import com.google.common.collect.ImmutableSet;
import lombok.Getter;

import java.util.*;

public class CoreGuard implements Guard {
    private RedstoneCore core;

    @Getter
    private Set<Handler> handlers;

    private final Map<String, Flag> flags;

    public CoreGuard(RedstoneCore instance) {
        this.core = instance;

        // Initialize collections
        handlers = new HashSet<>();
        flags = new HashMap<>();

        // Register default flags
        for (Flag<?> flag : Flags.flags.values()) {
            registerFlag(flag);
        }

        // Add default handlers
        registerHandler(new EntryHandler(core.getEventManager()));
        registerHandler(new ExitHandler(core.getEventManager()));
        registerHandler(new BuildHandler(core.getEventManager()));
    }

    @Override
    public void registerRegion(Region region) {
        core.getLogger().info("Registering region [" + region.getName() + "] with name [" + region.getName() + ']');
        core.getRegionManager().registerRegion(region);
    }

    @Override
    public void registerRegions(Region... regions) {
        for (Region region : regions) {
            registerRegion(region);
        }
    }

    @Override
    public void registerFlag(Flag<?> flag) {
        flags.put(flag.getName(), flag);
    }

    @Override
    public Flag<?> getFlagByName(String name) throws FlagNotFoundException {
        if (!flags.containsKey(name)) throw new FlagNotFoundException(name);
        else return flags.get(name);
    }

    @Override
    public Session getSession(UUID playerUUID) {
        return core.getSessionManager().getSession(playerUUID);
    }

    @Override
    public void createSession(Player player) {

    }

    @Override
    public RegionSet getApplicableRegions(World world, Vector vector) {
        RegionSet result = new RegionSet();

        // Iterate through all available regions
        for (Region region : getRegions(world)) {
            // Check if the vector is in the region
            if (region.isWithin(vector.toBlockVector3())) {
                // Add the region to the result list
                result.add(region);
            }
        }

        return result;
    }

    @Override
    public RegionSet getApplicableRegions(Location location) {
        return getApplicableRegions(location.getWorld(), location.toVector());
    }

    @Override
    public Region getRegion(String name) {
        return core.getRegionManager().getRegionByName(name);
    }

    @Override
    public boolean regionExists(String name) {
        return core.getRegionManager().regionExists(name);
    }

    @Override
    public Set<String> getRegionNames() {
        return core.getRegionManager().getRegionNames();
    }

    @Override
    public void registerHandler(Handler handler) {
        handlers.add(handler);
    }

    @Override
    public Set<Handler> getHandlers() {
        return ImmutableSet.copyOf(handlers);
    }

    @Override
    public Region[] getRegions(World world) {
        return core.getRegionManager().getBakedRegions(world);
    }

    static {
        SELECT_WAND_ITEM.setName(Text.of("&6&lSelection Wand"));
        RESIZE_WAND_ITEM.setName(Text.of("&b&lResizing Wand"));
    }

}
