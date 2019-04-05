package com.altarisnine.networkcore.api.text.format;

import lombok.Getter;

import java.util.Objects;

public class TextFormat {
    @Getter final TextColor color;
    @Getter final TextStyle style;

    public static final TextFormat NONE = new TextFormat(TextColor.NONE, TextStyle.NONE);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextFormat that = (TextFormat) o;
        return color.equals(that.color) &&
                style.equals(that.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, style);
    }

    private TextFormat(TextColor color, TextStyle style) {
        this.color = color;
        this.style = style;
    }

    public static TextFormat of() {
        return new TextFormat(TextColor.NONE, TextStyle.NONE);
    }

    public static TextFormat of(TextColor color) {
        return new TextFormat(color, TextStyle.NONE);
    }

    public static TextFormat of(TextStyle style) {
        return new TextFormat(TextColor.NONE, style);
    }

    public static TextFormat of(TextColor color, TextStyle style) {
        return new TextFormat(color, style);
    }

    public TextFormat color(TextColor color) {
        return new TextFormat(color, style);
    }

    public TextFormat style(TextStyle style) {
        return new TextFormat(color, style);
    }

    @Override
    public String toString() {
        return "TextFormat{" +
                "color=" + color +
                ", style=" + style +
                '}';
    }
}
