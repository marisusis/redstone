package com.altarisnine.networkcore.bukkit.entity.living.slime;

import com.altarisnine.networkcore.api.entity.living.slime.MagmaCube;
import com.altarisnine.networkcore.bukkit.entity.living.BukkitSlime;

public class BukkitMagmaCube extends BukkitSlime implements MagmaCube {
    private final org.bukkit.entity.MagmaCube entity;

    public BukkitMagmaCube(org.bukkit.entity.MagmaCube entity) {
        super(entity);
        this.entity = entity;
    }
}
