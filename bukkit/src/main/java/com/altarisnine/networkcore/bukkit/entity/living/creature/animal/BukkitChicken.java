package com.altarisnine.networkcore.bukkit.entity.living.creature.animal;

import com.altarisnine.networkcore.api.entity.living.creature.animal.Chicken;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitAnimal;

public final class BukkitChicken extends BukkitAnimal implements Chicken {
    private final org.bukkit.entity.Chicken entity;

    public BukkitChicken(org.bukkit.entity.Chicken entity) {
        super(entity);
        this.entity = entity;
    }
}
