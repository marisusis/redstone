package com.altarisnine.redstone.api.entity;

import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.block.Material;

import java.util.List;
import java.util.Set;

// TODO armor stand with bukkit
// TODO implement potion effects
public interface LivingEntity extends Entity, ProjectileSource, Damageable {

    List<Block> getLineOfSight(Set<Material> transparent, int distance);

    Block getTargetBlock(Set<Material> transparent, int distance);

    boolean hasLineOfSight(Entity other);

    boolean isGliding();

    void setGliding(boolean gliding);

    void setCollidable(boolean collidable);

    boolean isCollidable();

}
