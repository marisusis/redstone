package com.altarisnine.redstone.bukkit.entity.living.creature;

import com.altarisnine.redstone.api.entity.living.creature.Monster;
import com.altarisnine.redstone.bukkit.entity.living.BukkitCreature;

public class BukkitMonster extends BukkitCreature implements Monster {
    private final org.bukkit.entity.Monster entity;

    public BukkitMonster(org.bukkit.entity.Monster entity) {
        super(entity);
        this.entity = entity;
    }
}
