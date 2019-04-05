package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Stray;

public final class BukkitStray extends BukkitSkeleton implements Stray {
    private final org.bukkit.entity.Stray entity;

    public BukkitStray(org.bukkit.entity.Stray entity) {
        super(entity);
        this.entity = entity;
    }
}
