package com.altarisnine.networkcore.api.guard.spatial.boundary;

import com.altarisnine.networkcore.api.world.World;
import lombok.Getter;

public abstract class AbstractBoundary implements Boundary {

    @Getter protected final World world;

    public AbstractBoundary(World world) {
        this.world = world;
    }
}
