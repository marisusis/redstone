package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Guardian;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public class BukkitGuardian extends BukkitMonster implements Guardian {
    private final org.bukkit.entity.Guardian entity;

    public BukkitGuardian(org.bukkit.entity.Guardian entity) {
        super(entity);
        this.entity = entity;
    }
}
