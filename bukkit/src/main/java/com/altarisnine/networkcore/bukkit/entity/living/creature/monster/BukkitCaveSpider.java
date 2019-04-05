package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.CaveSpider;

public final class BukkitCaveSpider extends BukkitSpider implements CaveSpider {
    private final org.bukkit.entity.CaveSpider entity;

    public BukkitCaveSpider(org.bukkit.entity.CaveSpider entity) {
        super(entity);
        this.entity = entity;
    }
}
