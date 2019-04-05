package com.altarisnine.redstone.api.text;

import com.altarisnine.redstone.api.text.format.TextColor;
import com.altarisnine.redstone.api.text.format.TextStyle;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class TextHelper {
    // Compile patterns
    private static final Pattern colorPattern = Pattern.compile("((?:&[a-f0-9]|^(?:&(?![a-f0-9])+|[^&]*)+)(?:&(?![a-f0-9])+|[^&]*)+)");
    private static final Pattern stylePattern = Pattern.compile("((?:&[k-o]|^(?:&(?![k-o])+|[^&]*)+)(?:&(?![k-o])+|[^&]*)+)");
    private static final Pattern actionPattern = Pattern.compile("(?:&\\[(.*?)&](?:&\\{(.*?)&})?(?:&\\((.*?)&\\))?)");
    private static final Pattern finalPattern = Pattern.compile("&[a-fk-o0-9]");

    static Text legacyStringToText(final String input) {
        // Empty text array
        ArrayList<Text> texts = new ArrayList<>();

        synchronized (texts) {

            // STAGE 1 - Match groups of color
            final Matcher stage1 = colorPattern.matcher(input);

            // STAGE 2 - match styles
            while (stage1.find()) {
                // Get match
                final String match1 = stage1.group();

                // Check first character and get color code
                final char color = (match1.charAt(0) == '&') ? match1.charAt(1) : 'f';

                // Convert to TextColor
                TextColor textColor = TextColor.getColorFromChar(color);

                // Match styles
                final Matcher stage2 = stylePattern.matcher((match1.charAt(0) == '&') ? match1.substring(2) : match1);

                TextStyle textStyle = TextStyle.NONE;
                textStyle.reset();

                while (stage2.find()) {
                    // Get match
                    final String match2 = stage2.group();

                    if (match2.length() < 1) {
                        texts.add(Text.builder("").build());
                        continue;
                    }

                    // Check first character and get style code
                    final char style = (match2.charAt(0) == '&') ? match2.charAt(1) : 'z';

                    switch (style) {
                        case 'k':
                            textStyle.magic();
                            break;
                        case 'l':
                            textStyle.bold();
                            break;
                        case 'm':
                            textStyle.strikethrough();
                            break;
                        case 'n':
                            textStyle.underline();
                            break;
                        case 'o':
                            textStyle.italic();
                            break;
                        case 'r':
                            textStyle.reset();
                            textColor = TextColor.NONE;
                            break;
                        default:
                            textStyle.reset();
                            break;
                    }

                    // TODO urls, actions


                    // Add to texts
                    texts.add(Text.builder(finalPattern.matcher(match2).replaceAll("")).style(textStyle).color(textColor).build());

                }
            }
        }

        final Text.Builder text = Text.builder(texts.get(0));

        for (int i1 = 1; i1 < texts.size(); i1++) {
            text.append(texts.get(i1));
        }

        return text.build();
    }
}
