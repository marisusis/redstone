package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Vex;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitVex extends BukkitMonster implements Vex {
    private final org.bukkit.entity.Vex entity;

    public BukkitVex(org.bukkit.entity.Vex entity) {
        super(entity);
        this.entity = entity;
    }
}
