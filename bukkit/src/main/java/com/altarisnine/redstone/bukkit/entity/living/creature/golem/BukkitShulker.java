package com.altarisnine.redstone.bukkit.entity.living.creature.golem;

import com.altarisnine.redstone.api.entity.living.creature.golem.Shulker;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitGolem;

public final class BukkitShulker extends BukkitGolem implements Shulker {
    private final org.bukkit.entity.Shulker entity;

    public BukkitShulker(org.bukkit.entity.Shulker entity) {
        super(entity);
        this.entity = entity;
    }
}
