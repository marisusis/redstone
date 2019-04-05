package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Silverfish;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitSilverfish extends BukkitMonster implements Silverfish {
    private final org.bukkit.entity.Silverfish entity;

    public BukkitSilverfish(org.bukkit.entity.Silverfish entity) {
        super(entity);
        this.entity = entity;
    }
}
