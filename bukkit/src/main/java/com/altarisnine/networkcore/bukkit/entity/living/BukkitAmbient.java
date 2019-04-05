package com.altarisnine.networkcore.bukkit.entity.living;

import com.altarisnine.networkcore.api.entity.living.Ambient;

public class BukkitAmbient extends BukkitLivingEntity implements Ambient {
    private final org.bukkit.entity.Ambient entity;

    public BukkitAmbient(org.bukkit.entity.Ambient entity) {
        super(entity);
        this.entity = entity;
    }
}
