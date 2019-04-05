package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Vindicator;

public final class BukkitVindicator extends BukkitIllager implements Vindicator {
    private final org.bukkit.entity.Vindicator entity;

    public BukkitVindicator(org.bukkit.entity.Vindicator entity) {
        super(entity);
        this.entity = entity;
    }
}
