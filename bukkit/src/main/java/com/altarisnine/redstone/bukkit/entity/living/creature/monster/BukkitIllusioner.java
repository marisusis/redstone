package com.altarisnine.redstone.bukkit.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.monster.Illusioner;

public final class BukkitIllusioner extends BukkitSpellcaster implements Illusioner {
    private final org.bukkit.entity.Illusioner entity;

    public BukkitIllusioner(org.bukkit.entity.Illusioner entity) {
        super(entity);
        this.entity = entity;
    }
}
