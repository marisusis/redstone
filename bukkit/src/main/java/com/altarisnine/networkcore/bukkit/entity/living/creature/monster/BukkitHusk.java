package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Husk;

public final class BukkitHusk extends BukkitZombie implements Husk {
    private final org.bukkit.entity.Husk entity;

    public BukkitHusk(org.bukkit.entity.Husk entity) {
        super(entity);
        this.entity = entity;
    }
}
