package com.altarisnine.networkcore.bukkit.entity.living.creature.npc;

import com.altarisnine.networkcore.api.entity.living.creature.npc.Villager;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitNPC;

public class BukkitVillager extends BukkitNPC implements Villager {
    private final org.bukkit.entity.Villager entity;

    public BukkitVillager(org.bukkit.entity.Villager entity) {
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
