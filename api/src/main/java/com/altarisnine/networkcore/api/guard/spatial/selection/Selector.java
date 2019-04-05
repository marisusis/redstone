package com.altarisnine.networkcore.api.guard.spatial.selection;

import com.altarisnine.networkcore.api.guard.spatial.boundary.Boundary;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.world.World;

// MUST add generics for boundary type
public interface Selector {
    void selectPrimary(VectorI3 point);
    void selectSecondary(VectorI3 point);
    VectorI3 getPrimaryPosition() throws IncompleteSelectionException;
    Boundary getBoundary() throws IncompleteSelectionException;
    World getWorld();
    void setWorld(World world);
    void clear();
    boolean isComplete();
}
