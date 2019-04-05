package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.ZombieVillager;

public final class BukkitZombieVillager extends BukkitZombie implements ZombieVillager {
    private final org.bukkit.entity.ZombieVillager entity;

    public BukkitZombieVillager(org.bukkit.entity.ZombieVillager entity) {
        super(entity);
        this.entity = entity;
    }
}
