package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Witch;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitWitch extends BukkitMonster implements Witch {
    private final org.bukkit.entity.Witch entity;

    public BukkitWitch(org.bukkit.entity.Witch entity) {
        super(entity);
        this.entity = entity;
    }
}
