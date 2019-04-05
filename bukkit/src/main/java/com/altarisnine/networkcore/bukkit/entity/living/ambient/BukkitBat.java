package com.altarisnine.networkcore.bukkit.entity.living.ambient;

import com.altarisnine.networkcore.api.entity.living.ambient.Bat;
import com.altarisnine.networkcore.bukkit.entity.living.BukkitAmbient;

public class BukkitBat extends BukkitAmbient implements Bat {
    private final org.bukkit.entity.Bat entity;

    public BukkitBat(org.bukkit.entity.Bat entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void setAwake(boolean awake) {
        entity.setAwake(awake);
    }

    @Override
    public boolean isAwake() {
        return entity.isAwake();
    }
}
