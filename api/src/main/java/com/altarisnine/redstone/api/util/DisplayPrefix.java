package com.altarisnine.redstone.api.util;

public final class DisplayPrefix {
    private Color color;
    private String value;

    public DisplayPrefix(Color color, String value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public String toString() {
        return color.getCode() + value;
    }

    public Color getColor() {
        return this.color;
    }

    public String getValue() {
        return this.value;
    }
}
