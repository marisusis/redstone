package com.altarisnine.redstone.bukkit.entity.living.creature;

import com.altarisnine.redstone.api.entity.living.creature.Golem;
import com.altarisnine.redstone.bukkit.entity.living.BukkitCreature;

public class BukkitGolem extends BukkitCreature implements Golem {
    private final org.bukkit.entity.Golem entity;

    public BukkitGolem(org.bukkit.entity.Golem entity) {
        super(entity);
        this.entity = entity;
    }
}
