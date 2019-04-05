package com.altarisnine.networkcore.bukkit.entity.living.creature;

import com.altarisnine.networkcore.api.entity.living.creature.NPC;
import com.altarisnine.networkcore.bukkit.entity.living.BukkitCreature;

public class BukkitNPC extends BukkitCreature implements NPC {
    private final org.bukkit.entity.NPC entity;

    public BukkitNPC(org.bukkit.entity.NPC entity) {
        super(entity);
        this.entity = entity;
    }
}
