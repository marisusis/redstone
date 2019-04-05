package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Spider;
import com.altarisnine.redstone.bukkit.entity.living.creature.BukkitMonster;

public class BukkitSpider extends BukkitMonster implements Spider {
    private final org.bukkit.entity.Spider entity;

    public BukkitSpider(org.bukkit.entity.Spider entity) {
        super(entity);
        this.entity = entity;
    }
}
