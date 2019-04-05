package com.altarisnine.redstone.bukkit.entity.living;

import com.altarisnine.redstone.api.entity.living.Ambient;

public class BukkitAmbient extends BukkitLivingEntity implements Ambient {
    private final org.bukkit.entity.Ambient entity;

    public BukkitAmbient(org.bukkit.entity.Ambient entity) {
        super(entity);
        this.entity = entity;
    }
}
