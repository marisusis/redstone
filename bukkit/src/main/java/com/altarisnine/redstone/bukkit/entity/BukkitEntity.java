package com.altarisnine.redstone.bukkit.entity;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.entity.Entity;
import com.altarisnine.redstone.api.util.Vector;
import com.altarisnine.redstone.api.world.Location;
import com.altarisnine.redstone.bukkit.Converter;

import java.util.UUID;

public class BukkitEntity implements Entity {
    private final org.bukkit.entity.Entity entity;

    public BukkitEntity(org.bukkit.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public void setDisplayName(String displayName) {
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
    public void setVelocity(Vector velocity) {
        entity.setVelocity(Converter.vector(velocity));
    }

    @Override
    public Vector getVelocity() {
        return Converter.vector(entity.getVelocity());
    }

    @Override
    public boolean isOnGround() {
        return entity.isOnGround();
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public boolean isDead() {
        return entity.isDead();
    }

    @Override
    public boolean isValid() {
        return entity.isValid();
    }

    @Override
    public float getFallDistance() {
        return entity.getFallDistance();
    }

    @Override
    public void setFallDistance(float distance) {
        entity.setFallDistance(distance);
    }

    @Override
    public boolean isInsideVehicle() {
        return entity.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return entity.leaveVehicle();
    }

    @Override
    public Entity getVehicle() {
        return Converter.entity(entity.getVehicle());
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        entity.setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return entity.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean flag) {
        entity.setGlowing(flag);
    }

    @Override
    public boolean isGlowing() {
        return entity.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        entity.setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return entity.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return entity.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        entity.setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return entity.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        entity.setGravity(gravity);
    }

    @Override
    public String getName() {
        Redstone.getApi().getLogger().debug(entity.toString());
        return entity.getName();
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    public final org.bukkit.entity.Entity getInstance() {
        return entity;
    }
}
