package com.altarisnine.redstone.api.text.format;

import lombok.Getter;

import java.util.Objects;

public class TextStyle {

    @Getter private boolean bold, strikethrough, underline, italic, magic;

    public static final TextStyle NONE = new TextStyle();

    public TextStyle() {
        this(false, false, false, false, false);
    }

    public TextStyle(boolean bold, boolean italic, boolean underline, boolean strikethrough, boolean magic) {
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.strikethrough = strikethrough;
        this.magic = magic;
    }

    public TextStyle bold() {
        bold = true;
        return this;
    }

    public TextStyle strikethrough() {
        strikethrough = true;
        return this;
    }

    public TextStyle underline() {
        underline = true;
        return this;
    }

    public TextStyle italic() {
        italic = true;
        return this;
    }

    public TextStyle magic() {
        magic = true;
        return this;
    }

    public TextStyle reset() {
        bold = false;
        strikethrough = false;
        italic = false;
        underline = false;
        magic = false;
        return this;
    }

    public TextStyle and(TextStyle... styles) {
        boolean retBold = bold;
        boolean retItalic = italic;
        boolean retUnderline = underline;
        boolean retStrikethrough = strikethrough;
        boolean retMagic = magic;


        for (TextStyle style : styles) {
            retBold = style.bold;
            retItalic = style.italic;
            retUnderline = style.underline;
            retStrikethrough = style.strikethrough;
            retMagic = style.magic;
        }

        return new TextStyle(retBold, retItalic, retUnderline, retStrikethrough, retMagic);
    }

    public String getCodes() {
        StringBuilder builder = new StringBuilder();

        if (bold) builder.append("&l");
        if (italic) builder.append("&o");
        if (underline) builder.append("&n");
        if (magic) builder.append("&k");
        if (strikethrough) builder.append("&m");

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextStyle textStyle = (TextStyle) o;
        return bold == textStyle.bold &&
                strikethrough == textStyle.strikethrough &&
                underline == textStyle.underline &&
                italic == textStyle.italic &&
                magic == textStyle.magic;
    }

    @Override
    public String toString() {
        return "TextStyle{" +
                "bold=" + bold +
                ", strikethrough=" + strikethrough +
                ", underline=" + underline +
                ", italic=" + italic +
                ", magic=" + magic +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(bold, strikethrough, underline, italic, magic);
    }
}
