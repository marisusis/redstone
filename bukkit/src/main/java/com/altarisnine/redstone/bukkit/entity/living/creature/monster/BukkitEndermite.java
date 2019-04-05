package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Endermite;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public class BukkitEndermite extends BukkitMonster implements Endermite {
    private final org.bukkit.entity.Endermite entity;

    public BukkitEndermite(org.bukkit.entity.Endermite entity) {
        super(entity);
        this.entity = entity;
    }
}
