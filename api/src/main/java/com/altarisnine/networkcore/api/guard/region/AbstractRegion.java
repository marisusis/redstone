package com.altarisnine.networkcore.api.guard.region;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.spatial.boundary.Boundary;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.world.World;
import lombok.Getter;

public abstract class AbstractRegion<B extends Boundary> implements Region<B> {
    @Getter protected String name;
    @Getter protected B boundary;

    public AbstractRegion(String name, B boundary) {
        this.name = name;
        this.boundary = boundary;
    }

    @Override
    public World getWorld() {
        return boundary.getWorld();
    }

    @Override
    public boolean isWithin(VectorI3 vector) {
        return boundary.isWithin(vector);
    }

    @Override
    public boolean isWithin(Player player) {
        return boundary.isWithin(player.getLocation().toVector().toBlockVector3());
    }

}
