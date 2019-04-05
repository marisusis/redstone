package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Husk;

public final class BukkitHusk extends BukkitZombie implements Husk {
    private final org.bukkit.entity.Husk entity;

    public BukkitHusk(org.bukkit.entity.Husk entity) {
        super(entity);
        this.entity = entity;
    }
}
