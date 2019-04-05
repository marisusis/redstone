package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.CaveSpider;

public final class BukkitCaveSpider extends BukkitSpider implements CaveSpider {
    private final org.bukkit.entity.CaveSpider entity;

    public BukkitCaveSpider(org.bukkit.entity.CaveSpider entity) {
        super(entity);
        this.entity = entity;
    }
}
