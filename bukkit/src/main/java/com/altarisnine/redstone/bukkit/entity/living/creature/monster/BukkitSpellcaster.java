package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Spellcaster;

public class BukkitSpellcaster extends BukkitIllager implements Spellcaster {
    private final org.bukkit.entity.Spellcaster entity;

    public BukkitSpellcaster(org.bukkit.entity.Spellcaster entity) {
        super(entity);
        this.entity = entity;
    }
}
