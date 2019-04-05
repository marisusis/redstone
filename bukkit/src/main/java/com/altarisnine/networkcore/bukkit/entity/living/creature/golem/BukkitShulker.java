package com.altarisnine.networkcore.bukkit.entity.living.creature.golem;

import com.altarisnine.networkcore.api.entity.living.creature.golem.Shulker;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitGolem;

public final class BukkitShulker extends BukkitGolem implements Shulker {
    private final org.bukkit.entity.Shulker entity;

    public BukkitShulker(org.bukkit.entity.Shulker entity) {
        super(entity);
        this.entity = entity;
    }
}
