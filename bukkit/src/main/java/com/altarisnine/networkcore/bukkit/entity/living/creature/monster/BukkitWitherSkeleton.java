package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.WitherSkeleton;

public final class BukkitWitherSkeleton extends BukkitSkeleton implements WitherSkeleton {
    private final org.bukkit.entity.WitherSkeleton entity;

    public BukkitWitherSkeleton(org.bukkit.entity.WitherSkeleton entity) {
        super(entity);
        this.entity = entity;
    }
}
