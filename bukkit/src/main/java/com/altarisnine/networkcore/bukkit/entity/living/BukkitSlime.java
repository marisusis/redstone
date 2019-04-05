package com.altarisnine.networkcore.bukkit.entity.living;

import com.altarisnine.networkcore.api.entity.living.slime.Slime;

public class BukkitSlime extends BukkitLivingEntity implements Slime {
    private final org.bukkit.entity.Slime entity;

    public BukkitSlime(org.bukkit.entity.Slime entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public int getSize() {
        return entity.getSize();
    }

    @Override
    public void setSize(int size) {
        entity.setSize(size);
    }
}
