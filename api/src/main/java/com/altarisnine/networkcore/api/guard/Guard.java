package com.altarisnine.networkcore.api.guard;

import com.altarisnine.networkcore.api.block.Material;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.flag.Flag;
import com.altarisnine.networkcore.api.guard.flag.FlagNotFoundException;
import com.altarisnine.networkcore.api.guard.handler.Handler;
import com.altarisnine.networkcore.api.guard.region.Region;
import com.altarisnine.networkcore.api.guard.region.RegionSet;
import com.altarisnine.networkcore.api.guard.session.Session;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.util.Vector;
import com.altarisnine.networkcore.api.world.Location;
import com.altarisnine.networkcore.api.world.World;

import java.util.Set;
import java.util.UUID;

public interface Guard {

    void registerRegion(Region region);

    // FIXME is this necessary?
    void registerRegions(Region... regions);

    void registerFlag(Flag<?> flag);

    Flag<?> getFlagByName(String name) throws FlagNotFoundException;

    Session getSession(UUID playerUUID);

    void createSession(Player player);

    RegionSet getApplicableRegions(World world, Vector vector);
    RegionSet getApplicableRegions(Location location);

    Region getRegion(String name);

    boolean regionExists(String name);

    Set<String> getRegionNames();

    void registerHandler(Handler handler);

    Set<Handler> getHandlers();

    Region[] getRegions(World world);

    Item SELECT_WAND_ITEM = new Item(new Material("minecraft", "blaze_rod"), 1);
    Item RESIZE_WAND_ITEM = new Item(new Material("minecraft", "abone"), 1);
}
