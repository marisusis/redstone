package com.altarisnine.networkcore.bukkit.entity.living;

import com.altarisnine.networkcore.api.entity.living.WaterMob;

public class BukkitWaterMob extends BukkitLivingEntity implements WaterMob {
    private final org.bukkit.entity.WaterMob entity;

    public BukkitWaterMob(org.bukkit.entity.WaterMob entity) {
        super(entity);
        this.entity = entity;
    }
}
