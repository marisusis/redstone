package com.altarisnine.redstone.api.test.util;

import com.altarisnine.redstone.api.util.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class VectorTest {

    @Test
    public void testAABB() {
        Assertions.assertFalse(new Vector((double) 0, (double) 0, (double) 0).inAABB(new Vector(1.0, 2.0, 4.0), new Vector(2.0, 4.0, 8.0)));
        Assertions.assertTrue(new Vector((double) 0, (double) 0, (double) 0).inAABB(new Vector(-1.0, -2.0, -4.0), new Vector(2.0, 4.0, 8.0)));
    }

    @Test
    public void testPlanarAABB() {
        Assertions.assertFalse(new Vector((double) 0, (double) 0, (double) 0).inPlanarAABB(new Vector(1.0, -10.0, 4.0), new Vector(2.0, 4.0, 8.0)));
        Assertions.assertTrue(new Vector((double) 0, (double) 0, (double) 0).inPlanarAABB(new Vector(-1.0, 10.0, -4.0), new Vector(2.0, 4.0, 8.0)));
    }

    @Test
    public void testPlanarTriangle() {
        Vector A = new Vector(-2.0, (double) 0, 2.0);
        Vector B = new Vector(4.0, (double) 0, 4.0);
        Vector C = new Vector((double) 0, (double) 0, -2.0);
        Assertions.assertTrue(new Vector(1.0, 1.0, 1.0).inPlanarTriangle(A, B, C));
        Assertions.assertFalse(new Vector(-3.0, 1.0, 1.0).inPlanarTriangle(A, B, C));
    }

    @Test
    public void testTriangleArea() {
        Vector A = new Vector(17.0, (double) 0, 22.0);
        Vector B = new Vector(35.0, (double) 0, 5.0);
        Vector C = new Vector((double) 0, (double) 0, (double) 0);

        Assertions.assertEquals(342.50d, Vector.calcArea(A, B, C));
    }

    @Test
    public void testInsideSphere() {
        Vector center = new Vector(3.0, 3.0, 3.0);

        Vector point = new Vector(4.0, 4.0, 4.0);

        Assertions.assertTrue(point.inSphere(center, 2.0));
        Assertions.assertFalse(point.inSphere(center, 0.5));

    }

    @Test
    public void testInsideCircle() {
        Vector center = new Vector(3.0, 3.0, 3.0);

        Vector point = new Vector(4.0, 100.0, 4.0);

        Assertions.assertTrue(point.inCircle(center, 2.0));
        Assertions.assertFalse(point.inCircle(center, 0.5));
    }

}
