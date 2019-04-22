package com.altarisnine.redstone.api.guard;

import com.altarisnine.redstone.api.block.Material;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.flag.Flag;
import com.altarisnine.redstone.api.guard.flag.FlagNotFoundException;
import com.altarisnine.redstone.api.guard.handler.Handler;
import com.altarisnine.redstone.api.guard.region.Region;
import com.altarisnine.redstone.api.guard.region.RegionSet;
import com.altarisnine.redstone.api.guard.session.Session;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.server.Server;
import com.altarisnine.redstone.api.util.Vector;
import com.altarisnine.redstone.api.world.Location;
import com.altarisnine.redstone.api.world.World;

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

    Server getServer();

    Region[] getRegions(World world);

    Item SELECT_WAND_ITEM = new Item(new Material("minecraft", "blaze_rod"), 1);
    Item RESIZE_WAND_ITEM = new Item(new Material("minecraft", "abone"), 1);
}
