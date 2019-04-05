package com.altarisnine.networkcore.common.entity.living.player;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.entity.living.player.OfflinePlayer;
import com.altarisnine.networkcore.api.util.Rank;
import com.altarisnine.networkcore.common.NetworkCore;
import com.mojang.authlib.GameProfile;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class CoreOfflinePlayer implements OfflinePlayer {

    protected final NetworkCore core;
    protected final GameProfile profile;

    public CoreOfflinePlayer(NetworkCore instance, GameProfile profile) {
        this.core = instance;
        this.profile = profile;
    }

    @Override
    public void setMuted(boolean muted) {
        setField("muted", Boolean.toString(muted));
    }

    @Override
    public boolean isMuted() {
        return Boolean.parseBoolean(getField("muted"));
    }

    @Override
    public final void setRank(Rank rank) {
        setField("group", rank.toString());
    }

    @Override
    public final Rank getRank() {
        return Rank.valueOf(getField("group"));
    }

    @Override
    public final void setDisplayRank(Rank rank) {
        setField("displayRank", rank.toString());
    }

    @Override
    public final Rank getDisplayRank() {
        return Rank.valueOf(getField("displayRank"));
    }

    @Override
    public void ban() {
        setField("banned", Boolean.toString(true));
    }

    @Override
    public void unban() {
        setField("banned", Boolean.toString(false));
    }

    @Override
    public boolean isBanned() {
        return Boolean.parseBoolean(getField("banned"));
    }

    @Override
    public LocalDateTime getLastActive() {
        return LocalDateTime.parse(getField("lastJoined"));
    }

    @Override
    public void updateLastActive() {
        setField("lastJoined", LocalDateTime.now().toString());
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public void setField(String key, String value) {
        Core.getApi().getDatabase().writeGroupValue("players", profile.getId().toString(), key, value);
    }

    @Override
    public String getField(String key) {
        return Core.getApi().getDatabase().readGroupValue("players", profile.getId().toString(), key);
    }

    @Override
    public String getName() {
        return profile.getName();
    }

    @Override
    public UUID getUniqueId() {
        return profile.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfflinePlayer)) return false;
        OfflinePlayer that = (OfflinePlayer) o;
        return Objects.equals(getUniqueId(), that.getUniqueId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniqueId());
    }
}
