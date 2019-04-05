package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.ZombieVillager;

public final class BukkitZombieVillager extends BukkitZombie implements ZombieVillager {
    private final org.bukkit.entity.ZombieVillager entity;

    public BukkitZombieVillager(org.bukkit.entity.ZombieVillager entity) {
        super(entity);
        this.entity = entity;
    }
}
