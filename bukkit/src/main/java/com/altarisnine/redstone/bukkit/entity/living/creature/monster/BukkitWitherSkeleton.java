package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.WitherSkeleton;

public final class BukkitWitherSkeleton extends BukkitSkeleton implements WitherSkeleton {
    private final org.bukkit.entity.WitherSkeleton entity;

    public BukkitWitherSkeleton(org.bukkit.entity.WitherSkeleton entity) {
        super(entity);
        this.entity = entity;
    }
}
