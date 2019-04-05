package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.PigZombie;

public final class BukkitPigZombie extends BukkitZombie implements PigZombie {
    private final org.bukkit.entity.PigZombie entity;

    public BukkitPigZombie(org.bukkit.entity.PigZombie entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public int getAnger() {
        return entity.getAnger();
    }

    @Override
    public void setAnger(int level) {
        entity.setAnger(level);
    }

    @Override
    public void setAngry(boolean angry) {
        entity.setAngry(angry);
    }

    @Override
    public boolean isAngry() {
        return entity.isAngry();
    }
}
