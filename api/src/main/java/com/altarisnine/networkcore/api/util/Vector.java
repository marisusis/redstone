package com.altarisnine.networkcore.api.util;

import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI2;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Vector {

    @Getter @Setter private double x, y, z;

    public Vector() {
        this.x = (double) 0;
        this.y = (double) 0;
        this.z = (double) 0;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean inAABB(final Vector A, final Vector B) {
        final Vector min = minComponents(A, B);
        final Vector max = maxComponents(A, B);

        return (x <= max.x) &&
                (y <= max.y) &&
                (z <= max.z) &&
                (x >= min.x) &&
                (y >= min.y) &&
                (z >= min.z);
    }

    public boolean inPlanarAABB(final Vector A, final Vector B) {
        final Vector min = minComponents(A, B);
        final Vector max = maxComponents(A, B);

        return (x <= max.x) &&
                (z <= max.z) &&
                (x >= min.x) &&
                (z >= min.z);
    }

    public boolean inPlanarTriangle(final Vector A, final Vector B, final Vector C) {
        double total = calcArea(A, B, C);
        double a = calcArea(this, A, B);
        double b = calcArea(this, B, C);
        double c = calcArea(this, C, A);

        return (a + b + c) == total;
    }

    public boolean inSphere(final Vector center, final double radius) {
        return (MathUtils.square(center.x - x) + MathUtils.square(center.y - y) + MathUtils.square(center.z - z)) <= MathUtils.square(radius);
    }

    public boolean inCircle(final Vector center, final double radius) {
        return (MathUtils.square(center.x - x) + MathUtils.square(center.z - z)) <= MathUtils.square(radius);
    }

    public static double calcArea(final Vector A, final Vector B, final Vector C) {
        return Math.abs(((A.x * (B.z - C.z)) + (B.x * (C.z - A.z)) + (C.x * (A.z - B.z))) / 2.0);
    }

    public static Vector minComponents(final Vector A, final Vector B) {
        return new Vector(Math.min(A.x, B.x), Math.min(A.y, B.y), Math.min(A.z, B.z));
    }

    public static Vector maxComponents(final Vector A, final Vector B) {
        return new Vector(Math.max(A.x, B.x), Math.max(A.y, B.y), Math.max(A.z, B.z));
    }

    public static Vector of(VectorI2 vector) {
        return new Vector(vector.getX(), 0, vector.getZ());
    }

    public static Vector of(VectorI3 vector) {
        return new Vector(vector.getX(), vector.getY(), vector.getZ());
    }

    public VectorI2 toBlockVector2() {
        return new VectorI2(x, z);
    }

    public VectorI3 toBlockVector3() {
        return new VectorI3(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (getClass() != obj.getClass())) return false;
        Vector vector = (Vector) obj;
        return (Double.compare(vector.x, x) == 0) &&
                (Double.compare(vector.y, y) == 0) &&
                (Double.compare(vector.z, z) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
