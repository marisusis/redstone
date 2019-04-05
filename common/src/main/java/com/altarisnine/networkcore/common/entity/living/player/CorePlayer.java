package com.altarisnine.networkcore.common.entity.living.player;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.entity.living.player.OfflinePlayer;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.spatial.boundary.Boundary;
import com.altarisnine.networkcore.api.guard.spatial.selection.ColumnSelector;
import com.altarisnine.networkcore.api.guard.spatial.selection.Selector;
import com.altarisnine.networkcore.api.util.Rank;
import com.altarisnine.networkcore.api.util.RedundancyException;
import com.altarisnine.networkcore.common.NetworkCore;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;

public abstract class CorePlayer implements Player {

    private final UUID uuid;
    protected final NetworkCore core;

    @Getter
    private Selector selector;

    protected CorePlayer(NetworkCore instance, UUID uuid) {
        if (Core.getApi().getPlayer(uuid) != null) throw new RedundancyException(Player.class);
        this.uuid = uuid;
        this.core = instance;
        // TODO allow changing of selector type9
        selector = new ColumnSelector();
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public void changeSelectorType(Class<? extends Selector> selectorType) {
        try {
            selector = selectorType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void select(Boundary boundary) {
        selector = boundary.getSelector();
    }

    @Override
    public void ban() {
        // Set player to banned
        setField("banned", Boolean.toString(true));

        // Kick player
        kick("You have been banned!");
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
        return LocalDateTime.now();
    }

    @Override
    public boolean isOnline() {
        return true;
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
        setField("rank", rank.toString());
    }

    @Override
    public final Rank getRank() {
        return Rank.valueOf(getField("rank"));
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
    public String getName() {
        return getField("username");
    }

    @Override
    public void setDisplayName(String string) {
        throw new RuntimeException("Cannot set display name of a Player!");
    }

    // TODO eventually change back to display group
    @Override
    public String getDisplayName() {
        return getRank().getPrefix().toString() + getName();
    }

    @Override
    public boolean hasClearance(Rank rank) {
        return !getRank().isLowerThan(rank);
    }

    @Override
    public void updateLastActive() {
        setField("lastJoined", LocalDateTime.now(ZoneId.of("EST")).toString());
    }

    @Override
    public final void setField(String key, String value) {
        core.getSyncManager().writeGroupValue("players", uuid.toString(), key, value);
    }

    @Override
    public final String getField(String key) {
        return core.getSyncManager().readGroupValue("players", uuid.toString(), key);
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
