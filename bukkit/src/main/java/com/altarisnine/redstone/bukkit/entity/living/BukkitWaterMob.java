package com.altarisnine.redstone.bukkit.entity.living;

import com.altarisnine.redstone.api.entity.living.WaterMob;

public class BukkitWaterMob extends BukkitLivingEntity implements WaterMob {
    private final org.bukkit.entity.WaterMob entity;

    public BukkitWaterMob(org.bukkit.entity.WaterMob entity) {
        super(entity);
        this.entity = entity;
    }
}
