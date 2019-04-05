package com.altarisnine.redstone.api.text.format;

import com.altarisnine.redstone.api.util.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TextColor {

    private static final Map<Character, TextColor> charMap = new HashMap<>();

    public static final TextColor NONE = new TextColor(Color.RESET, 'r');
    public static final TextColor BLACK = new TextColor(Color.BLACK, '0');
    public static final TextColor DARK_BLUE = new TextColor(Color.DARK_BLUE, '1');
    public static final TextColor DARK_GREEN = new TextColor(Color.DARK_GREEN, '2');
    public static final TextColor DARK_AQUA = new TextColor(Color.DARK_AQUA, '3');
    public static final TextColor DARK_RED = new TextColor(Color.DARK_RED, '4');
    public static final TextColor DARK_PURPLE = new TextColor(Color.DARK_PURPLE, '5');
    public static final TextColor GOLD = new TextColor(Color.GOLD, '6');
    public static final TextColor GRAY = new TextColor(Color.GRAY, '7');
    public static final TextColor DARK_GRAY = new TextColor(Color.DARK_GRAY, '8');
    public static final TextColor BLUE = new TextColor(Color.BLUE, '9');
    public static final TextColor GREEN = new TextColor(Color.GREEN, 'a');
    public static final TextColor AQUA = new TextColor(Color.AQUA, 'b');
    public static final TextColor RED = new TextColor(Color.RED, 'c');
    public static final TextColor LIGHT_PURPLE = new TextColor(Color.LIGHT_PURPLE, 'd');
    public static final TextColor YELLOW = new TextColor(Color.YELLOW, 'e');
    public static final TextColor WHITE = new TextColor(Color.WHITE, 'f');
    public static final TextColor RESET = new TextColor(Color.RESET, 'r');

    @Getter @Setter private final Color color;
    @Getter @Setter private final char marker;

    TextColor(Color color, char marker) {
        this.color = color;
        this.marker = marker;
        charMap.put(marker, this);
    }

    public static TextColor getColorFromChar(char c) {
        return charMap.get(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextColor textColor = (TextColor) o;
        return color == textColor.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
