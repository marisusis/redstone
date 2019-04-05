package com.altarisnine.networkcore.api.guard.spatial.selection;

import com.altarisnine.networkcore.api.guard.spatial.boundary.Boundary;
import com.altarisnine.networkcore.api.guard.spatial.boundary.ColumnBoundary;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI2;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.world.World;

public final class ColumnSelector implements Selector {
    private VectorI2 pos1;
    private VectorI2 pos2;
    private ColumnBoundary boundary;
    private World world;

    public ColumnSelector() {
        boundary = new ColumnBoundary(world, VectorI2.ZERO, VectorI2.ZERO);
    }

    @Override
    public void selectPrimary(final VectorI3 point) {
        pos1 = point.toBlockVector2();
    }

    @Override
    public void selectSecondary(final VectorI3 point) {
        pos2 = point.toBlockVector2();
    }

    @Override
    public VectorI3 getPrimaryPosition() throws IncompleteSelectionException {
        if (pos1 == null) throw new IncompleteSelectionException();
        return pos1.toBlockVector3();
    }

    @Override
    public Boundary getBoundary() throws IncompleteSelectionException {
        if (!isComplete()) { // FIXME  why the hell isn't this working
            throw new IncompleteSelectionException();
        }
        return new ColumnBoundary(world, pos1, pos2);
    }


    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void clear() {
        pos1 = null;
        pos2 = null;
    }

    @Override
    public boolean isComplete() {
        return (pos1 != null && pos2 != null);
    }

}
