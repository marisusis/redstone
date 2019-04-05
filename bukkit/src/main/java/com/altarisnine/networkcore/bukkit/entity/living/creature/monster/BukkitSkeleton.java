package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Skeleton;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public class BukkitSkeleton extends BukkitMonster implements Skeleton {
    private final org.bukkit.entity.Skeleton entity;

    public BukkitSkeleton(org.bukkit.entity.Skeleton entity) {
        super(entity);
        this.entity = entity;
    }
}
