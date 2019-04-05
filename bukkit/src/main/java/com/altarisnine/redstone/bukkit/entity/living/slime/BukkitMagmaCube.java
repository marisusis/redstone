package com.altarisnine.redstone.bukkit.entity.living.slime;

import com.altarisnine.redstone.api.entity.living.slime.MagmaCube;
import com.altarisnine.redstone.bukkit.entity.living.BukkitSlime;

public class BukkitMagmaCube extends BukkitSlime implements MagmaCube {
    private final org.bukkit.entity.MagmaCube entity;

    public BukkitMagmaCube(org.bukkit.entity.MagmaCube entity) {
        super(entity);
        this.entity = entity;
    }
}
