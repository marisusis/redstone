package com.altarisnine.networkcore.common.external;

import com.mojang.authlib.GameProfile;

public interface MojangManager {
    GameProfile getProfileFromName(String username);
}
