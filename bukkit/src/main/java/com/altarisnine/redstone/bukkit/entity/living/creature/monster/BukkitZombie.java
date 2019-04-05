package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Zombie;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public class BukkitZombie extends BukkitMonster implements Zombie {
    private final org.bukkit.entity.Zombie entity;

    public BukkitZombie(org.bukkit.entity.Zombie entity) {
        super(entity);
        this.entity = entity;
    }
}
