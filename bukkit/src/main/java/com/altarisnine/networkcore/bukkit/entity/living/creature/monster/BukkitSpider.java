package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Spider;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public class BukkitSpider extends BukkitMonster implements Spider {
    private final org.bukkit.entity.Spider entity;

    public BukkitSpider(org.bukkit.entity.Spider entity) {
        super(entity);
        this.entity = entity;
    }
}
