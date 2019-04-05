package com.altarisnine.networkcore.bukkit.entity.living.creature.animal;

import com.altarisnine.networkcore.api.entity.living.creature.animal.MushroomCow;

public final class BukkitMushroomCow extends BukkitCow implements MushroomCow {
    private final org.bukkit.entity.MushroomCow entity;

    public BukkitMushroomCow(org.bukkit.entity.MushroomCow entity) {
        super(entity);
        this.entity = entity;
    }
}
