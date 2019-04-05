package com.altarisnine.networkcore.api.test.guard;

import com.altarisnine.networkcore.api.guard.spatial.boundary.Boundary;
import com.altarisnine.networkcore.api.guard.spatial.boundary.CubicBoundary;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.world.World;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

class CubicBoundaryTest {

    static Boundary boundary;

//    @BeforeAll

    static void setup() {
        World world = Mockito.mock(World.class);
        Mockito.when(world.getName()).thenReturn("aTestWorld");

        boundary = new CubicBoundary(world, new VectorI3(-10, 80, -10),
                new VectorI3(10, 100, 10));
    }

//    @Test
    void getArea() {
        Assertions.assertEquals(400, boundary.getArea());
    }

//    @Test
    void isWithin() {
        Assertions.assertTrue(boundary.isWithin(new VectorI3(0, 90, 0)));
        Assertions.assertFalse(boundary.isWithin(new VectorI3(0, 0, 0)));
    }

}