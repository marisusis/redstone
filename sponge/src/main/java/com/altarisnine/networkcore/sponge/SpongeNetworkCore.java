package com.altarisnine.networkcore.sponge;

import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.api.APIBase;
import com.altarisnine.networkcore.common.bootstrap.NetworkCoreBootstrap;
import com.altarisnine.networkcore.common.user.User;
import com.altarisnine.networkcore.common.util.ServerType;
import com.altarisnine.networkcore.sponge.api.SpongeAPI;
import com.altarisnine.networkcore.sponge.user.SpongeUser;
import org.spongepowered.api.Sponge;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class SpongeNetworkCore extends NetworkCore {
    public SpongeNetworkCore(NetworkCoreBootstrap instance) {
        super(instance);
    }

    @Override
    public User getPlayer(UUID uuid) {
        return new SpongeUser(Sponge.getServer().getPlayer(uuid).get());
    }

    @Override
    public User getPlayerByName(String name) {
        return new SpongeUser(Sponge.getServer().getPlayer(name).get());
    }

    @Override
    public List<User> getOnlineUsers() {
        return null;
    }

    @Override
    public void broadcast(String message) {

    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void log(String message) {

    }

    @Override
    public ServerType getType() {
        return ServerType.INSTANCE;
    }

    @Override
    public APIBase getAPIImpl() {
        return new SpongeAPI(this);
    }

    @Override
    public File getDataFolder() {
        return null;
    }
}
