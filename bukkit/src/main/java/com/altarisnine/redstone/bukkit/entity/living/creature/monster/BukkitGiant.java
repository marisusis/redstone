package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Giant;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitGiant extends BukkitMonster implements Giant {
    private final org.bukkit.entity.Giant entity;

    public BukkitGiant(org.bukkit.entity.Giant entity) {
        super(entity);
        this.entity = entity;
    }
}
