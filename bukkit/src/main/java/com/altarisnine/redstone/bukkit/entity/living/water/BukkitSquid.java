package com.altarisnine.redstone.bukkit.entity.living.water;

import com.altarisnine.redstone.api.entity.living.water.Squid;
import com.altarisnine.redstone.bukkit.entity.living.BukkitWaterMob;

public class BukkitSquid extends BukkitWaterMob implements Squid {
    private final org.bukkit.entity.Squid entity;

    public BukkitSquid(org.bukkit.entity.Squid entity) {
        super(entity);
        this.entity = entity;
    }
}
