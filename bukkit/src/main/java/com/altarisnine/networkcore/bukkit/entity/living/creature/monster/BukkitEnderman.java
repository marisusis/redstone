package com.altarisnine.networkcore.bukkit.entity.living.creature.monster;

import com.altarisnine.networkcore.api.block.Material;
import com.altarisnine.networkcore.api.entity.living.creature.monster.Enderman;
import com.altarisnine.networkcore.bukkit.Converter;
import com.altarisnine.networkcore.bukkit.entity.living.creature.BukkitMonster;

public class BukkitEnderman extends BukkitMonster implements Enderman {
    private final org.bukkit.entity.Enderman entity;

    public BukkitEnderman(org.bukkit.entity.Enderman entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public Material getCarrying() {
        return Converter.blockType(entity.getCarriedMaterial().getItemType());
    }

    // TODO the heck is MaterialData
    @Override
    public void setCarrying(Material material) {
        try {
            entity.setCarriedMaterial(Converter.blockType(material).getData().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
