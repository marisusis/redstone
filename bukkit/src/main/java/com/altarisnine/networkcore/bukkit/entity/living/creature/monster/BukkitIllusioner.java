package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.monster.Illusioner;

public final class BukkitIllusioner extends BukkitSpellcaster implements Illusioner {
    private final org.bukkit.entity.Illusioner entity;

    public BukkitIllusioner(org.bukkit.entity.Illusioner entity) {
        super(entity);
        this.entity = entity;
    }
}
