package com.altarisnine.redstone.bukkit.entity.living.creature.animal;

import com.altarisnine.redstone.api.entity.living.creature.animal.Ocelot;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitAnimal;

public final class BukkitOcelot extends BukkitAnimal implements Ocelot {
    private final org.bukkit.entity.Ocelot entity;

    public BukkitOcelot(org.bukkit.entity.Ocelot entity) {
        super(entity);
        this.entity = entity;
    }
}
