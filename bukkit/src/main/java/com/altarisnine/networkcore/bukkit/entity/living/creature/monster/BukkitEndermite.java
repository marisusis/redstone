package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Endermite;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public class BukkitEndermite extends BukkitMonster implements Endermite {
    private final org.bukkit.entity.Endermite entity;

    public BukkitEndermite(org.bukkit.entity.Endermite entity) {
        super(entity);
        this.entity = entity;
    }
}
