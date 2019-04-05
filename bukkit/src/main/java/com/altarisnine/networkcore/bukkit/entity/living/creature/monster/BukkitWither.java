package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Wither;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitWither extends BukkitMonster implements Wither {
    private final org.bukkit.entity.Wither entity;

    public BukkitWither(org.bukkit.entity.Wither entity) {
        super(entity);
        this.entity = entity;
    }
}
