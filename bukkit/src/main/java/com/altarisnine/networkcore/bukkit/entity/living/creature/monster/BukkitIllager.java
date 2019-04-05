package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Illager;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public class BukkitIllager extends BukkitMonster implements Illager {
    private final org.bukkit.entity.Illager entity;

    public BukkitIllager(org.bukkit.entity.Illager entity) {
        super(entity);
        this.entity = entity;
    }
}
