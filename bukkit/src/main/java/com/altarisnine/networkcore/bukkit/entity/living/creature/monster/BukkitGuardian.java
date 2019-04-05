package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Guardian;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public class BukkitGuardian extends BukkitMonster implements Guardian {
    private final org.bukkit.entity.Guardian entity;

    public BukkitGuardian(org.bukkit.entity.Guardian entity) {
        super(entity);
        this.entity = entity;
    }
}
