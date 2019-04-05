package com.altarisnine.redstone.api.entity.living.player;

import com.altarisnine.redstone.api.storage.sync.Syncable;
import com.altarisnine.redstone.api.util.Rank;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OfflinePlayer extends Syncable {

    void setRank(Rank rank);
    Rank getRank();

    void setMuted(boolean muted);
    boolean isMuted();

    void setDisplayRank(Rank rank);
    Rank getDisplayRank();

    void ban();
    void unban();
    boolean isBanned();

    LocalDateTime getLastActive(); // Can be null
    void updateLastActive(); // Can be null

    boolean isOnline();


    String getName();
    UUID getUniqueId();
}
