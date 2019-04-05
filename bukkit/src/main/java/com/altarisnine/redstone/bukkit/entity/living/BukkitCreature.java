package com.altarisnine.redstone.bukkit.entity.living;

import com.altarisnine.redstone.api.entity.LivingEntity;
import com.altarisnine.redstone.api.entity.living.Creature;

public class BukkitCreature extends BukkitLivingEntity implements Creature {
    private final org.bukkit.entity.Creature entity;

    public BukkitCreature(org.bukkit.entity.Creature entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void setTarget(LivingEntity target) {
        throw new UnsupportedOperationException("not implemented!");
    }

    @Override
    public LivingEntity getTarget() {
        throw new UnsupportedOperationException("not implemented!");
    }
}
