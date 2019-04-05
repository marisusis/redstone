package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Evoker;

public final class BukkitEvoker extends BukkitSpellcaster implements Evoker {
    private final org.bukkit.entity.Evoker entity;

    public BukkitEvoker(org.bukkit.entity.Evoker entity) {
        super(entity);
        this.entity = entity;
    }
}
