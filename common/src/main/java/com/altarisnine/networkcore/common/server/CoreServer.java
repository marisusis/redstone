package com.altarisnine.networkcore.common.server;

import com.altarisnine.networkcore.api.entity.living.player.OfflinePlayer;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.Guard;
import com.altarisnine.networkcore.api.plugin.PluginManager;
import com.altarisnine.networkcore.api.scheduling.Scheduler;
import com.altarisnine.networkcore.api.server.Server;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.common.NetworkCore;

import java.util.UUID;

public abstract class CoreServer implements Server {

    protected final NetworkCore core;

    public CoreServer(NetworkCore instance) {
        core = instance;
    }

    @Override
    public int getMaxPlayers() {
        return 0;
    }

    @Override
    public final Player getPlayer(UUID uuid) {
        return core.getPlayerManager().getPlayer(uuid);
    }

    @Override
    public final Player getPlayer(String name) {
        return core.getPlayerManager().getPlayer(name);
    }

    @Override
    public void stop() {

    }

    @Override
    public final OfflinePlayer getOfflinePlayer(String name) {
        return core.getPlayerManager().getOfflinePlayer(name);
    }

    @Override
    public final OfflinePlayer getOfflinePlayer(UUID id) {
        return null;
    }

    @Override
    public final Guard getGuard() {
         return core.getGuard();
    }

    @Override
    public final PluginManager getPluginManager() {
        return core.getPluginManager();
    }

    @Override
    public final Scheduler getScheduler() {
        return core.getNetworkScheduler();
    }

    @Override
    public final void announce(final Text message) {
        core.getPlayerManager().getActivePlayers().values().forEach(p -> p.sendMessage(message));
        core.getLogger().info(message.getPlaintext());
    }
}
