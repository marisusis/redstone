package com.altarisnine.redstone.bukkit.entity.living.creature;

import com.altarisnine.redstone.api.entity.living.creature.Ageable;
import com.altarisnine.redstone.bukkit.entity.living.BukkitCreature;

public class BukkitAgeable extends BukkitCreature implements Ageable {

    private final org.bukkit.entity.Ageable entity;

    public BukkitAgeable(org.bukkit.entity.Ageable entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public int getAge() {
        return entity.getAge();
    }

    @Override
    public void setAge(int age) {
        entity.setAge(age);
    }

    @Override
    public void setLockAge(boolean lock) {
        entity.setAgeLock(lock);
    }

    @Override
    public boolean getLockAge() {
        return entity.getAgeLock();
    }

    @Override
    public void setBaby() {
        entity.setBaby();
    }

    @Override
    public void setAdult() {
        entity.setAdult();
    }

    @Override
    public boolean isAdult() {
        return entity.isAdult();
    }

    @Override
    public boolean canBreed() {
        return entity.canBreed();
    }

    @Override
    public void setBreed(boolean breed) {
        entity.setBreed(breed);
    }
}
