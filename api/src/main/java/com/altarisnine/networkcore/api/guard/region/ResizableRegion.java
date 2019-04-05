package com.altarisnine.networkcore.api.guard.region;

import com.altarisnine.networkcore.api.guard.session.Session;
import com.altarisnine.networkcore.api.guard.spatial.boundary.ColumnBoundary;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI2;

@Deprecated
public abstract class ResizableRegion extends AbstractRegion<ColumnBoundary> {

    public ResizableRegion(String name, ColumnBoundary boundary) {
        super(name, boundary);
    }

    public void attemptResize(VectorI2 prevCorner, VectorI2 newCorner) {
        // Check if the previous point is still a corner
        if (!isCornerPoint(prevCorner)) throw new IllegalStateException("The point specified is not currently a corner point");

        int prevX = prevCorner.getX();
        int prevZ = prevCorner.getZ();
        int newX = newCorner.getX();
        int newZ = newCorner.getZ();

        // Get the point that would be diagonally opposite from the previous points
        VectorI2 opposite;
        if (prevCorner.getX() == boundary.getMin().getX()) opposite = VectorI2.of(boundary.getMax().getX(), boundary.getMin().getZ());
        else opposite = VectorI2.of(boundary.getMin().getX(), boundary.getMax().getZ());

        // Update boundary points
        boundary.updatePoints(newCorner, opposite);
    }

    public boolean isCornerPoint(VectorI2 vector) {
        return (vector.getX() == boundary.getMin().getX() || vector.getX() == boundary.getMax().getX())
                && (vector.getZ() == boundary.getMin().getZ() || vector.getX() == boundary.getMax().getZ());
    }

    public abstract boolean canResize(Session session);
}
