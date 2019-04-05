package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Zombie;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public class BukkitZombie extends BukkitMonster implements Zombie {
    private final org.bukkit.entity.Zombie entity;

    public BukkitZombie(org.bukkit.entity.Zombie entity) {
        super(entity);
        this.entity = entity;
    }
}
