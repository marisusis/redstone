package com.altarisnine.networkcore.api.guard.spatial;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class GridUtils {
    public static String printGraph(int width, int height, List<Point> points, String... symbols) {
        StringBuilder builder = new StringBuilder();
        int[][] grid = generateGrid(width, height);

        for (Point point : points) {
            grid[point.getY() - 1][point.getX() - 1] = 1;
        }

        for (int i = grid.length - 1; i >= 0; i--) {
            builder.append('\n');
            for (int cell : grid[i]) {
                builder.append(" ");
                builder.append(symbols[cell]);
            }
        }

        return builder.toString();
    }


    public static int[][] generateGrid(int width, int height) {
        int[][] grid = new int[height][width];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }

        return grid;
    }

    @Test
    public void testGraph() {
        System.out.println(printGraph(8, 8, Arrays.asList(
                new Point(2, 2, 1),
                new Point(4, 4, 2)
        ), " ", "O", "X"));
    }

    public static class Point {
        @Getter
        private final int x, y, value;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
