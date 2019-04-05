package com.altarisnine.redstone.api.util;

import lombok.Getter;

import java.util.LinkedList;

public final class Path {

    public static final String EMPTY = " "; // □
    public static final String OBSTACLE = "☒"; // ■
    public static final String PATH = "■"; // ☒


    @Getter private LinkedList<Position> steps;

    @Getter
    private final int[][] map;

    public Path(int[][] map) {
        this.map = map;
        // Initialize list of steps
        steps = new LinkedList<>();
    }

    public void addStep(Position position) {
        steps.add(position);
    }

    public int size() {
        return steps.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Position step : steps) {
            builder.append(step.toString());
        }

        return builder.toString();
    }

    public String getGrid() {
        StringBuilder builder = new StringBuilder();

        // Convert map into strings
        String[][] stringMap = new String[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int cell = map[i][j];
                stringMap[i][j] = cell != 0 ? OBSTACLE : EMPTY;
            }
        }

        for (Position step : steps) {
            stringMap[step.getX()][step.getY()] = PATH;
        }

        for (int i = 0; i < stringMap.length; i++) {
            for (String string : stringMap[i]) {
                builder.append(string);
                builder.append(' ');
            }

            builder.append(i);
            builder.append('\n');
        }

        return builder.toString();
    }
}
