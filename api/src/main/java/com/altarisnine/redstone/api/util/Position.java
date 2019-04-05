package com.altarisnine.redstone.api.util;

import lombok.Getter;

import java.util.Objects;

public final class Position {
    @Getter private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position add(Position position) {
        return new Position(x + position.x, y + position.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (getClass() != obj.getClass())) return false;
        Position position = (Position) obj;
        return (x == position.x) &&
                (y == position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
