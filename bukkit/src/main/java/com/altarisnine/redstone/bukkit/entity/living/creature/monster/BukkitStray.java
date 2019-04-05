package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Stray;

public final class BukkitStray extends BukkitSkeleton implements Stray {
    private final org.bukkit.entity.Stray entity;

    public BukkitStray(org.bukkit.entity.Stray entity) {
        super(entity);
        this.entity = entity;
    }
}
