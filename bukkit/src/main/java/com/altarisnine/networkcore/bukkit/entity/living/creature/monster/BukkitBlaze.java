package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Blaze;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitBlaze extends BukkitMonster implements Blaze {
    private final org.bukkit.entity.Blaze entity;

    public BukkitBlaze(org.bukkit.entity.Blaze entity) {
        super(entity);
        this.entity = entity;
    }
}
