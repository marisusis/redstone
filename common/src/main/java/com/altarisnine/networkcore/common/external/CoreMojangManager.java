package com.altarisnine.networkcore.common.external;

import com.altarisnine.networkcore.common.NetworkCore;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.server.UserCache;

import java.io.File;
import java.net.Proxy;
import java.util.UUID;

public class CoreMojangManager implements MojangManager {
    private final NetworkCore core;

    private UserCache userCache;

    public CoreMojangManager(NetworkCore core) {
        this.core = core;

        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
        MinecraftSessionService sessionService = service.createMinecraftSessionService();
        GameProfileRepository profileRepository = service.createProfileRepository();
        this.userCache = new UserCache(profileRepository, new File(core.getDataFolder(), "usercache.json"));
    }

    public GameProfile getProfileFromName(String username) {
        return this.userCache.getProfile(username);
    }
}
