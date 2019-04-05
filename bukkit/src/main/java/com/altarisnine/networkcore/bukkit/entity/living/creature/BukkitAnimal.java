package com.altarisnine.networkcore.bukkit.entity.living.creature;

import com.altarisnine.networkcore.api.entity.living.creature.animal.Animal;

public class BukkitAnimal extends BukkitAgeable implements Animal {
    private final org.bukkit.entity.Animals entity;

    public BukkitAnimal(org.bukkit.entity.Animals entity) {
        super(entity);
        this.entity = entity;
    }
}
