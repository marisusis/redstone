package com.altarisnine.redstone.api.guard.region;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.flag.Flag;
import com.altarisnine.redstone.api.guard.flag.PermissibleFlag;
import com.altarisnine.redstone.api.guard.spatial.boundary.Boundary;
import com.altarisnine.redstone.api.util.Rank;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public abstract class FlaggedRegion<B extends Boundary> extends AbstractRegion<B> {

    @Getter protected final Map<Flag<?>, Object> flags;

    public FlaggedRegion(String name, B boundary) {
        super(name, boundary);
        this.flags = new HashMap<>();
    }

    public <T> boolean setFlag(Flag<T> flag, T value) {
        flags.put(flag, value);
        return true;
    }

    public <T> T queryFlagState(Flag<T> flag) {
        if (!flags.containsKey(flag)) return flag.getDefault();
        return (T) flags.get(flag);
    }

    @Override
    public boolean testFlagFor(Player player, PermissibleFlag flag) {
        switch (queryFlagState(flag)) {
            case ALLOW:
                return true;
            case DENY:
                return false;
            case ADMIN:
            case SRMOD:
            case MOD:
            case TRAINEE:
            case BUILDER:
                Rank targetRank = Rank.valueOf(flag.toString());
                return player.hasClearance(targetRank);
            default:
                return true;
        }
    }
}
