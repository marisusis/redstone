package com.altarisnine.networkcore.bukkit.entity.living.creature.animal;

import com.altarisnine.networkcore.api.entity.living.creature.animal.Parrot;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitAnimal;

public final class BukkitParrot extends BukkitAnimal implements Parrot {
    private final org.bukkit.entity.Parrot entity;

    public BukkitParrot(org.bukkit.entity.Parrot entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public Parrot.Variant getVariant() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void setVariant(Parrot.Variant variant) {
        throw new UnsupportedOperationException("not implemented");
    }
}
