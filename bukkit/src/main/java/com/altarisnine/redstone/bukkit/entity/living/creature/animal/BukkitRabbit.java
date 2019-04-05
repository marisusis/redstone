package com.altarisnine.redstone.bukkit.entity.living.creature.animal;

import com.altarisnine.redstone.api.entity.living.creature.animal.Rabbit;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitAnimal;

public final class BukkitRabbit extends BukkitAnimal implements Rabbit {
    private final org.bukkit.entity.Rabbit entity;

    public BukkitRabbit(org.bukkit.entity.Rabbit entity) {
        super(entity);
        this.entity = entity;
    }
}
