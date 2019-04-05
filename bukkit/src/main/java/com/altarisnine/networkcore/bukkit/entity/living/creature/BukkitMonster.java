package com.altarisnine.networkcore.bukkit.entity.living.creature;

import com.altarisnine.networkcore.api.entity.living.creature.Monster;
import com.altarisnine.networkcore.bukkit.entity.living.BukkitCreature;

public class BukkitMonster extends BukkitCreature implements Monster {
    private final org.bukkit.entity.Monster entity;

    public BukkitMonster(org.bukkit.entity.Monster entity) {
        super(entity);
        this.entity = entity;
    }
}
