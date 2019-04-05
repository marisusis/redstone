package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Illager;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public class BukkitIllager extends BukkitMonster implements Illager {
    private final org.bukkit.entity.Illager entity;

    public BukkitIllager(org.bukkit.entity.Illager entity) {
        super(entity);
        this.entity = entity;
    }
}
