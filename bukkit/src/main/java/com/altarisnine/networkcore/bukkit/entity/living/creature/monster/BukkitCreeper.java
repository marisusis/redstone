package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Creeper;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public final class BukkitCreeper extends BukkitMonster implements Creeper {
    private final org.bukkit.entity.Creeper entity;

    public BukkitCreeper(org.bukkit.entity.Creeper entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public boolean isPowered() {
        return entity.isPowered();
    }

    @Override
    public void setPowered(boolean powered) {
        entity.setPowered(powered);
    }
}
