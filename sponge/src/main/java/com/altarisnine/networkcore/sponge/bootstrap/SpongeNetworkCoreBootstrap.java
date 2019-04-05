package com.altarisnine.networkcore.sponge.bootstrap;

import com.altarisnine.networkcore.common.bootstrap.NetworkCoreBootstrap;
import com.altarisnine.networkcore.sponge.SpongeNetworkCore;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "networkcore", name="SpongeNetworkCore", version="1.0-SNAPSHOT", description="The Core.")
public class SpongeNetworkCoreBootstrap implements NetworkCoreBootstrap {
    private final SpongeNetworkCore core;

    public SpongeNetworkCoreBootstrap() {
        core = new SpongeNetworkCore(this);
    }

    public NetworkCoreBootstrap getBootstrap() {
        return this;
    }

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("NetworkCoreSponge started");
    }
}
