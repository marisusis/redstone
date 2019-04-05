package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Silverfish;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitSilverfish extends BukkitMonster implements Silverfish {
    private final org.bukkit.entity.Silverfish entity;

    public BukkitSilverfish(org.bukkit.entity.Silverfish entity) {
        super(entity);
        this.entity = entity;
    }
}
