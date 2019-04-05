package com.altarisnine.networkcore.bukkit.entity.living.creature.golem;

import com.altarisnine.networkcore.api.entity.living.creature.golem.Snowman;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitGolem;

public final class BukkitSnowman extends BukkitGolem implements Snowman {
    private final org.bukkit.entity.Snowman entity;

    public BukkitSnowman(org.bukkit.entity.Snowman entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public boolean isDerp() {
        return entity.isDerp();
    }

    @Override
    public void setDerp(boolean derp) {
        entity.setDerp(derp);
    }
}
