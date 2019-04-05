package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Witch;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitWitch extends BukkitMonster implements Witch {
    private final org.bukkit.entity.Witch entity;

    public BukkitWitch(org.bukkit.entity.Witch entity) {
        super(entity);
        this.entity = entity;
    }
}
