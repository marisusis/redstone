package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.ElderGuardian;

public final class BukkitElderGuardian extends BukkitGuardian implements ElderGuardian {
    private final org.bukkit.entity.ElderGuardian entity;

    public BukkitElderGuardian(org.bukkit.entity.ElderGuardian entity) {
        super(entity);
        this.entity = entity;
    }
}
