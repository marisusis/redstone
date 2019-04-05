package com.altarisnine.redstone.sponge;

import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.api.APIBase;
import com.altarisnine.redstone.common.bootstrap.RedstoneCoreBootstrap;
import com.altarisnine.redstone.common.user.User;
import com.altarisnine.redstone.common.util.ServerType;
import com.altarisnine.redstone.sponge.api.SpongeAPI;
import com.altarisnine.redstone.sponge.user.SpongeUser;
import org.spongepowered.api.Sponge;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class SpongeRedstoneCore extends RedstoneCore {
    public SpongeRedstoneCore(RedstoneCoreBootstrap instance) {
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
