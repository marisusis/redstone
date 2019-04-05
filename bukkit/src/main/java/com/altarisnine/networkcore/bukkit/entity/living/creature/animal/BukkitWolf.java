package com.altarisnine.networkcore.bukkit.entity.living.creature.animal;

import com.altarisnine.networkcore.api.entity.living.creature.animal.Wolf;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitAnimal;

public final class BukkitWolf extends BukkitAnimal implements Wolf {
    private final org.bukkit.entity.Wolf entity;

    public BukkitWolf(org.bukkit.entity.Wolf entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public boolean isAngry() {
        return entity.isAngry();
    }

    @Override
    public void setAngry(boolean angry) {
        entity.setAngry(angry);
    }
}
