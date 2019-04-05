package com.altarisnine.redstone.sponge.api;

import com.altarisnine.redstone.api.world.World;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.api.APIBase;

public class SpongeAPI extends APIBase {
    public SpongeAPI(RedstoneCore instance) {
        super(instance);
    }

    @Override
    public World getWorld(String name) {
        return null;
    }
}
