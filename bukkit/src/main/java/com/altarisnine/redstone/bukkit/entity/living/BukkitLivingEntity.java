package com.altarisnine.redstone.bukkit.entity.living;

import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.block.Material;
import com.altarisnine.redstone.api.entity.Entity;
import com.altarisnine.redstone.api.entity.LivingEntity;
import com.altarisnine.redstone.api.world.Location;
import com.altarisnine.redstone.bukkit.Converter;
import com.altarisnine.redstone.bukkit.entity.BukkitEntity;
import com.altarisnine.redstone.bukkit.world.block.BukkitBlock;

import java.util.*;

public class BukkitLivingEntity extends BukkitEntity implements LivingEntity {

    private final org.bukkit.entity.LivingEntity entity;

    public BukkitLivingEntity(org.bukkit.entity.LivingEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void damage(double damage) {
        entity.damage(damage);
    }

    @Override
    public void damage(double amount, Entity source) {
        throw new UnsupportedOperationException("not implemented!");
    }

    @Override
    public void setHealth(double health) {
        entity.setHealth(health);
    }

    @Override
    public double getHealth() {
        return entity.getHealth();
    }

    @Override
    public void kill() {
        entity.setHealth((double) 0);
    }

    @Override
    public void setDisplayName(String string) {
        throw new UnsupportedOperationException("not implemented!");
    }

    @Override
    public String getDisplayName() {
        throw new UnsupportedOperationException("not implemented!");
    }

    @Override
    public Location getLocation() {
        return Converter.location(entity.getLocation());
    }

    @Override
    public void teleport(Location to) {
        entity.teleport(Converter.location(to));
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int distance) {
        Set<org.bukkit.Material> transparents = EnumSet.noneOf(org.bukkit.Material.class);

        for (Material material : transparent) {
            transparents.add(Converter.blockType(material));
        }

        List<Block> result = new ArrayList<>();

        for (org.bukkit.block.Block block : entity.getLineOfSight(transparents, distance)) {
            result.add(new BukkitBlock(block));
        }

        return result;
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int distance) {
        Set<org.bukkit.Material> transparents = EnumSet.noneOf(org.bukkit.Material.class);

        for (Material material : transparent) {
            transparents.add(Converter.blockType(material));
        }

        return new BukkitBlock(entity.getTargetBlock(transparents, distance));
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        throw new UnsupportedOperationException("not implemented!");
    }

    @Override
    public boolean isGliding() {
        return entity.isGliding();
    }

    @Override
    public void setGliding(boolean gliding) {
        entity.setGliding(gliding);
    }

    @Override
    public void setCollidable(boolean collidable) {
        entity.setCollidable(collidable);
    }

    @Override
    public boolean isCollidable() {
        return entity.isCollidable();
    }
}
