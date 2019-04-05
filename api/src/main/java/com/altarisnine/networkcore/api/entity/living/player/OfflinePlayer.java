package com.altarisnine.networkcore.api.entity.living.player;

import com.altarisnine.networkcore.api.storage.sync.Syncable;
import com.altarisnine.networkcore.api.util.Rank;

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
