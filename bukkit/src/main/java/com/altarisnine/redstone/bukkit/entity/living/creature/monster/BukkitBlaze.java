package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Blaze;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitBlaze extends BukkitMonster implements Blaze {
    private final org.bukkit.entity.Blaze entity;

    public BukkitBlaze(org.bukkit.entity.Blaze entity) {
        super(entity);
        this.entity = entity;
    }
}
