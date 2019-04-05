package com.altarisnine.redstone.bukkit.entity.living.creature;

import com.altarisnine.redstone.api.entity.living.creature.NPC;
import com.altarisnine.redstone.bukkit.entity.living.BukkitCreature;

public class BukkitNPC extends BukkitCreature implements NPC {
    private final org.bukkit.entity.NPC entity;

    public BukkitNPC(org.bukkit.entity.NPC entity) {
        super(entity);
        this.entity = entity;
    }
}
