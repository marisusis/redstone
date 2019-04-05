package com.altarisnine.networkcore.bukkit.entity.living.creature.animal;

import com.altarisnine.networkcore.api.entity.living.creature.animal.Cow;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitAnimal;

public class BukkitCow extends BukkitAnimal implements Cow {
    private final org.bukkit.entity.Cow entity;

    public BukkitCow(org.bukkit.entity.Cow entity) {
        super(entity);
        this.entity = entity;
    }
}
