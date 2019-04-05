package com.altarisnine.redstone.sponge.bootstrap;

import com.altarisnine.redstone.common.bootstrap.RedstoneCoreBootstrap;
import com.altarisnine.redstone.sponge.SpongeRedstoneCore;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "redstone", name="SpongeRedstoneCore", version="1.0-SNAPSHOT", description="The Redstone.")
public class SpongeRedstoneCoreBootstrap implements RedstoneCoreBootstrap {
    private final SpongeRedstoneCore core;

    public SpongeRedstoneCoreBootstrap() {
        core = new SpongeRedstoneCore(this);
    }

    public RedstoneCoreBootstrap getBootstrap() {
        return this;
    }

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("NetworkCoreSponge started");
    }
}
