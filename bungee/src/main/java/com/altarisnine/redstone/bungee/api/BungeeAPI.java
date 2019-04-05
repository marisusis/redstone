package com.altarisnine.redstone.bungee.api;

import com.altarisnine.redstone.api.world.World;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.api.APIBase;

public final class BungeeAPI extends APIBase {
    public BungeeAPI(RedstoneCore instance) {
        super(instance);
    }

    @Override
    public World getWorld(String name) {
        throw new UnsupportedOperationException("BungeeCord not allowed!");
    }
}
