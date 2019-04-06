package com.altarisnine.redstone.api.guard.region;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.flag.Flag;
import com.altarisnine.redstone.api.guard.flag.PermissibleFlag;
import com.altarisnine.redstone.api.guard.spatial.boundary.Boundary;
import com.altarisnine.redstone.api.guard.spatial.vector.VectorI3;
import com.altarisnine.redstone.api.world.World;

public interface Region<B extends Boundary> {
    boolean testFlagFor(Player player, PermissibleFlag flag);
    <T> boolean setFlag(Flag<T> flag, T value);

    String getName();
    World getWorld();

    B getBoundary();

    boolean isWithin(VectorI3 vector);
    boolean isWithin(Player player);

}
