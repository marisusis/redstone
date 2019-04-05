package com.altarisnine.networkcore.bungee.api;

import com.altarisnine.networkcore.api.world.World;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.api.APIBase;

public final class BungeeAPI extends APIBase {
    public BungeeAPI(NetworkCore instance) {
        super(instance);
    }

    @Override
    public World getWorld(String name) {
        throw new UnsupportedOperationException("BungeeCord not allowed!");
    }
}
