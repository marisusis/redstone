package com.altarisnine.networkcore.api.guard.spatial;

import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI2;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class VisualizationTest {

    @Test
    void testOpposite() {
        Assertions.assertEquals(2, getOppositeIndex(5, 2));
        Assertions.assertEquals(4, getOppositeIndex(5, 0));
        Assertions.assertEquals(523, getOppositeIndex(524, 0));
    }

    @Test
    void testEndpoints() {
        Assertions.assertArrayEquals(new byte[] {1,0,0,0,1}, fillEndpoints(new byte[] {0,0,0,0,0}));
        Assertions.assertArrayEquals(new byte[] {1,0,0,1}, fillEndpoints(new byte[] {0,0,0,0}));
        Assertions.assertArrayEquals(new byte[] {1,1,0,0,1}, fillEndpoints(new byte[] {0,1,0,0,0}));
        Assertions.assertArrayEquals(new byte[] {1,1,0,1}, fillEndpoints(new byte[] {0,1,0,0}));
    }

    @Test
    void testReflect() {
        Assertions.assertArrayEquals(new byte[] {1,0,0,1,1,0,1}, reflect(new byte[] {1,0,1,1,0,0,1}));
    }

    @Test
    void testHalf() {
        Assertions.assertArrayEquals(new byte[] {1,0,0}, half(new byte[] {1,0,0,1,0,0,1}));
        Assertions.assertArrayEquals(new byte[] {1,0,0,1}, half(new byte[] {1,0,0,1,1,0,0,1}));

        // If even, get the first half; if odd, get the first half, leaving out the center
    }

    @Test
    void testGraph() {
        printGraph(10, 10, ImmutableSet.of(VectorI2.of(1,1), VectorI2.of(1,0)));
    }

    @Test
    void testAlgorithm() {
        System.out.println(ArrayUtils.toString(algorithm(110)));
    }

    @Test
    void testBlockBorder() {
        Collection<VectorI2> points = blockBorder(VectorI2.of(2, 2), VectorI2.of(20, 10));
        printGraph(40, 40, points);
    }

    Set<VectorI2> blockBorder(VectorI2 A, VectorI2 B) {
        Set<VectorI2> result = new HashSet<>();

        // Tackle x rows first
        VectorI2 min = VectorI2.minComponents(A, B);
        VectorI2 max = VectorI2.maxComponents(A, B);

        int xLen = max.getX() - min.getX();
        int zLen = max.getZ() - min.getZ();

        byte[] row = algorithm(xLen);
        byte[] column = algorithm(zLen);

        result.add(min);
        result.add(max);

        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1) {
                result.add(VectorI2.of(min.getX()  + i, min.getZ()));
                result.add(VectorI2.of(min.getX() + i, max.getZ()));
            }
        }

        for (int i = 0; i < column.length; i++) {
            if (column[i] == 1) {
                result.add(VectorI2.of(min.getX(), min.getZ() + i));
                result.add(VectorI2.of(max.getX(), min.getZ() + i));
            }
        }


        return ImmutableSet.copyOf(result);
    }

    byte[] algorithm(final int len) {
        boolean even = len % 2 == 0;

        byte[] half = half(new byte[len]);

        for (int i = 0; i < half.length; i += 3) {
            half[i] = 1;
        }

        if (even) {
            return ArrayUtils.addAll(half, reflect(half));
        } else {
            return ArrayUtils.addAll(ArrayUtils.addAll(half, new byte[]{1}), reflect(half));
        }
    }

    byte[] half(byte[] input) {
        if (input.length % 2 == 0) {
            return ArrayUtils.subarray(input,0, input.length / 2);
        } else {
            return ArrayUtils.subarray(input, 0, (int) Math.floor(input.length / 2d));
        }
    }

    byte[] reflect(byte[] input) {
        byte[] output = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            output[input.length - (i + 1)] = input[i];
        }

        return output;
    }

    byte[] fillEndpoints(byte[] input) {
        final byte[] output = input;

        output[0] = 1;
        output[output.length - 1] = 1;

        return input;
    }

    int getOppositeIndex(int length, int index) {
        return length - (index + 1);
    }


    synchronized void printGraph(int width, int height, Collection<VectorI2> points) {
        byte[][] cells = new byte[height][width];
        for (VectorI2 point : points) {
            int x = point.getX();
            int y = point.getZ();
            if (x < 0 || y < 0 || x > width || y > height) {
                throw new IllegalArgumentException("points must be within the range");
            }
            cells[y][x] = 1;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = cells.length - 1; i >= 0; i--) {
            byte[] row = cells[i];
            builder.append("\n");

            for (byte cell : row) {
                builder.append((cell == 1) ? "X" : ".");
            }
        }

        System.out.println(builder.toString());
    }

}
