package com.altarisnine.networkcore.bukkit.entity.living.creature.animal;

import com.altarisnine.networkcore.api.entity.living.creature.animal.Sheep;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitAnimal;

public final class BukkitSheep extends BukkitAnimal implements Sheep {
    private final org.bukkit.entity.Sheep entity;

    public BukkitSheep(org.bukkit.entity.Sheep entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public boolean isSheared() {
        return entity.isSheared();
    }

    @Override
    public void setSheared(boolean sheared) {
        entity.setSheared(sheared);
    }
}
