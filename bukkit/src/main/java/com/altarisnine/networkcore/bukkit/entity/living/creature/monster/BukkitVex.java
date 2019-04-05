package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Vex;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitVex extends BukkitMonster implements Vex {
    private final org.bukkit.entity.Vex entity;

    public BukkitVex(org.bukkit.entity.Vex entity) {
        super(entity);
        this.entity = entity;
    }
}
