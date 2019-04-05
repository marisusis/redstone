package com.altarisnine.networkcore.sponge.api;

import com.altarisnine.networkcore.api.world.World;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.api.APIBase;

public class SpongeAPI extends APIBase {
    public SpongeAPI(NetworkCore instance) {
        super(instance);
    }

    @Override
    public World getWorld(String name) {
        return null;
    }
}
