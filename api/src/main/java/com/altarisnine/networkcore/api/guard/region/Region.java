package com.altarisnine.networkcore.api.guard.region;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.flag.Flag;
import com.altarisnine.networkcore.api.guard.flag.PermissibleFlag;
import com.altarisnine.networkcore.api.guard.spatial.boundary.Boundary;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.world.World;

public interface Region<B extends Boundary> {
    boolean testFlagFor(Player player, PermissibleFlag flag);
    <T> void setFlag(Flag<T> flag, T value);

    String getName();
    World getWorld();

    B getBoundary();

    boolean isWithin(VectorI3 vector);
    boolean isWithin(Player player);

}
