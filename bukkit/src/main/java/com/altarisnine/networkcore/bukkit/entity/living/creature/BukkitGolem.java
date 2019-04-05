package com.altarisnine.networkcore.bukkit.entity.living.creature;

import com.altarisnine.networkcore.api.entity.living.creature.Golem;
import com.altarisnine.networkcore.bukkit.entity.living.BukkitCreature;

public class BukkitGolem extends BukkitCreature implements Golem {
    private final org.bukkit.entity.Golem entity;

    public BukkitGolem(org.bukkit.entity.Golem entity) {
        super(entity);
        this.entity = entity;
    }
}
