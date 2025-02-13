package com.altarisnine.redstone.api.guard.spatial.boundary;

import com.altarisnine.redstone.api.world.World;
import lombok.Getter;

public abstract class AbstractBoundary implements Boundary {

    @Getter protected final World world;

    public AbstractBoundary(World world) {
        this.world = world;
    }
}
