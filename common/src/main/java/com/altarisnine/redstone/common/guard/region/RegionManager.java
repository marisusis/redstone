package com.altarisnine.redstone.common.guard.region;

import com.altarisnine.redstone.api.configuration.Configuration;
import com.altarisnine.redstone.api.configuration.ConfigurationSection;
import com.altarisnine.redstone.api.guard.region.Region;
import com.altarisnine.redstone.api.guard.region.SimpleRegion;
import com.altarisnine.redstone.api.world.World;
import com.altarisnine.redstone.common.RedstoneCore;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class RegionManager {
    private final RedstoneCore core;

    private final HashMap<String, Region> loadedRegions;

    private final Map<World, Region[]> bakedRegions = new HashMap<>();

    public RegionManager(RedstoneCore instance) {
        this.core = instance;
        loadedRegions = new HashMap<>();
    }

    public void loadAll() {
        // Load regions from config file
        Configuration regions = core.getConfigurationManager().getConfig("regions.yml");

        regions.getValues(false).forEach((key, part) -> {
            if (part instanceof ConfigurationSection) {
                SimpleRegion region = regions.get(SimpleRegion.class, key);
                core.getGuard().registerRegion(region);
            }
        });
    }

    public synchronized void registerRegion(Region region) {
        // Check if region is already registered
        if (!loadedRegions.containsKey(region.getName().toLowerCase())) {
            loadedRegions.put(region.getName().toLowerCase(), region);
        }

        if (!bakedRegions.containsKey(region.getWorld())) bakedRegions.put(region.getWorld(), new Region[0]);

        // Get current baked list
        Region[] baked = bakedRegions.get(region.getWorld());

        // Bake regions
        bakedRegions.put(region.getWorld(), ArrayUtils.add(baked, region));
    }

    public Region getRegionByName(String name) {
        return loadedRegions.get(name.toLowerCase());
    }

    public boolean regionExists(String name) {
        return loadedRegions.containsKey(name.toLowerCase());
    }

    public Set<String> getRegionNames() {
        return loadedRegions.keySet();
    }

    public Region[] getBakedRegions(World world) {
        if (bakedRegions.containsKey(world)) {
            return bakedRegions.get(world);
        } else {
            return new Region[0];
        }
    }


}
