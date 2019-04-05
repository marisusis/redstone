package com.altarisnine.redstone.common.external;

import com.mojang.authlib.GameProfile;

public interface MojangManager {
    GameProfile getProfileFromName(String username);
}
