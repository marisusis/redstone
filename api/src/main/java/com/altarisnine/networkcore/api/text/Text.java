package com.altarisnine.networkcore.api.text;

import com.altarisnine.networkcore.api.text.format.TextColor;
import com.altarisnine.networkcore.api.text.format.TextFormat;
import com.altarisnine.networkcore.api.text.format.TextStyle;
import com.google.common.collect.ImmutableList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// PERFORMANCE use localization and resources file & compile Text.of calls on boot to keep performance up
// PERFORMANCE use json with tellRaw instead of converting to text objects in wrapper, allows for global usage
public class Text {
    private static final String FORMATTING_CHAR = "&";

    @Getter private String content;
    @Getter final TextFormat format;
    @Getter final ImmutableList<Text> children;


    Text() {
        this.format = TextFormat.NONE;
        this.children = ImmutableList.of();
    }

    Text(String content, TextFormat format, ImmutableList<Text> children) {
        this.content = content;
        this.format = format;
        this.children = children;
    }

    public String toFormat() {
        return content.replace('&', '§');
    }

    public static Text of(String text, Object... parts) {
        return of(String.format(text, parts));
    }

    public static Text of(String text) {
        return TextHelper.legacyStringToText(text);
//        return new Text(text, TextFormat.NONE, null);
    }

    public static Text fromLegacyText(String text) {

        // Split text by color chars first (not formatting chars)

        return null;
    }

    public static class Builder {

        private String content;

        TextFormat format = TextFormat.NONE;
        List<Text> children = new ArrayList<>();
        Builder() {
            this("");
        }

        Builder(String content) {
            this.content = content;
        }

        Builder(Text text) {
            this.format = text.format;
            this.children = new ArrayList<>(text.children);
            this.content = text.getContent();
        }

        public Builder color(TextColor color) {
            format = format.color(color);
            return this;
        }

        public Builder style(TextStyle... styles) {
            format = format.style(format.getStyle().and(styles));
            return this;
        }

        public Builder append(Text... children) {
            Collections.addAll(this.children, children);
            return this;
        }

        public Text build() {
            return new Text(
                    this.content,
                    this.format,
                    ImmutableList.copyOf(this.children)
            );
        }

    }
    public static Text.Builder builder(String stuff) {
        return new Builder(stuff);
    }

    public static Text.Builder builder(Text stuff) {
        return new Builder(stuff);
    }

    public String getPlaintext() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.content);

        this.children.forEach(text -> {
            builder.append(text.getPlaintext());
        });

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return content.equals(text.content) &&
                format.equals(text.format) &&
                Objects.equals(children, text.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, format, children);
    }

    @Override
    public String toString() {
        return "Text{" +
                "content='" + content + '\'' +
                ", format=" + format +
                ", children=" + children +
                '}';
    }
}
