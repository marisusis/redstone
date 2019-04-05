package com.altarisnine.redstone.bukkit.entity.living.creature.animal;

import com.altarisnine.redstone.api.entity.living.creature.animal.Pig;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitAnimal;

public final class BukkitPig extends BukkitAnimal implements Pig {
    private final org.bukkit.entity.Pig entity;

    public BukkitPig(org.bukkit.entity.Pig entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public boolean hasSaddle() {
        return entity.hasSaddle();
    }

    @Override
    public void setSaddle(boolean saddled) {
        entity.setSaddle(saddled);
    }
}
