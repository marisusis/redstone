package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Giant;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitGiant extends BukkitMonster implements Giant {
    private final org.bukkit.entity.Giant entity;

    public BukkitGiant(org.bukkit.entity.Giant entity) {
        super(entity);
        this.entity = entity;
    }
}
