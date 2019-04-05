package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Spellcaster;

public class BukkitSpellcaster extends BukkitIllager implements Spellcaster {
    private final org.bukkit.entity.Spellcaster entity;

    public BukkitSpellcaster(org.bukkit.entity.Spellcaster entity) {
        super(entity);
        this.entity = entity;
    }
}
