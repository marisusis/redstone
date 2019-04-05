package com.altarisnine.networkcore.bukkit.entity.living.creature.golem;

import com.altarisnine.networkcore.api.entity.living.creature.golem.IronGolem;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitGolem;

public final class BukkitIronGolem extends BukkitGolem implements IronGolem {
    private final org.bukkit.entity.IronGolem entity;

    public BukkitIronGolem(org.bukkit.entity.IronGolem entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public boolean isPlayerCreated() {
        return entity.isPlayerCreated();
    }

    @Override
    public void setPlayerCreated(boolean playerCreated) {
        entity.setPlayerCreated(playerCreated);
    }
}
