package com.altarisnine.redstone.api.text;

import com.altarisnine.redstone.api.text.format.TextColor;
import com.altarisnine.redstone.api.text.format.TextStyle;
import com.altarisnine.redstone.api.util.Pair;
import com.altarisnine.redstone.api.util.Triplet;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class TextTest {

    private static char[] colors = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static char[] format = new char[]{'k', 'l', 'm', 'n', 'o'};
    public static String F = "&";
    static Pattern P = Pattern.compile("&([lmnor])");
    static Pattern FIRST = Pattern.compile("(&[abcdef1234567890klmnor][^&]*)");

//    @Test
    public void testText() {
        String content = "This &kis a &c&l[NOTICE]: &7The &nserver &7will rest&m&lart in approximately &6&o7 minutes&7. Please save your work!";

        // PREPARE
        content = content.replace(' ', '•');

        // STAGE 1
        // Create matcher
        Matcher matcher1 = FIRST.matcher(content);

        // Stage 2 input
        ArrayList<String> stage2 = new ArrayList<>();

        // Iterate until there are no more matches
        while (matcher1.find()) {
            // Get the match and load into builder
            StringBuilder match = new StringBuilder(matcher1.group());

            // Combine consecutive color codes
            while (matcher1.group().length() <= 2) {
                matcher1.find();
                match.append(matcher1.group());
            }

//            out.format("Matched: %s%n", match.toString());
            stage2.add(match.toString());
        }

        // STAGE 3 - distribute color codes over formatting codes
        ArrayList<String> stage3 = new ArrayList<>();

        for (int i = 0; i < stage2.size(); i++) {
            // Get item
            String item = stage2.get(i);

            // TODO If there is no color char present, concatenate
            char code = item.charAt(1);
//            out.format("Stage3 [#%d]: Char: %s, Item: %s%n", i, code, item);

            StringBuilder staging3 = new StringBuilder(item);
            int j = i;
            // If the char is not a color formatting char, then add all formatting from before (start with color)
            while (!isColorChar(code)) {
                code = stage2.get(--j).charAt(1);
                staging3.insert(0, "&" + code);
//                out.format("[#%d]: %s%n", i, staging3);
            }

            stage3.add(staging3.toString());
        }

        StringBuilder stringBuilder = new StringBuilder();


        for (String s : stage3) {
            stringBuilder.append(s);
//            out.format("Stage3: %s%n", s);
        }

        String processed = stringBuilder.toString().replace('•', ' ');

       /* out.format("Final Result: %s%n", processed);
        out.format("Formatted Result: %s,%nFormatted Original: %s%n%n", processed.replace('&','§'),
                content.replace('•', ' ').replace('&','§'));*/

        ArrayList<Pair<TextColor, String>> stage4 = new ArrayList<>();

        // STAGE 4 - Extract colors from text
        for (String s : stage3) {
            // Get color char
            char color = s.charAt(1);

            // Get value
            String staging4 = s.substring(2);

            // Convert to TextColor
            TextColor textColor = TextColor.getColorFromChar(color);

//            out.format("Stage4: Color: %s, Text: %s%n", textColor.toString(), staging4);

            // Add to next stage
            stage4.add(new Pair<>(textColor, staging4));
        }

        ArrayList<Triplet<String, TextColor, TextStyle>> stage5 = new ArrayList<>();

        // STAGE 5 - extract styles and create final results
        for (Pair<TextColor, String> pair : stage4) {
            // Create blank text style
            TextStyle textStyle = TextStyle.NONE;
            Matcher matcher = P.matcher(pair.getB());

            String staging5 = pair.getB();

            while (matcher.find()) {
                //out.format("Stage5 [%s]: Match: %s%n", pair.getValue(), matcher.group(1));
                staging5 = pair.getB().substring(matcher.end());
                char style = matcher.group(1).charAt(0);
                // Check the style
                switch (style) {
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
                    case 'k':
                        textStyle.magic();
                        break;
                    case 'r':
                        textStyle.reset();
                        break;
                }
            }

//            out.format("Stage5 [%s]: TextStyle: %s%n", staging5, textStyle.toString());
            stage5.add(new Triplet<>(staging5, pair.getA(), textStyle));
        }

        // STAGE 6 - convert to text
        int i = 0;
        // TODO create empty Text.builder method
        Text.Builder builder = Text.builder("");
        while (i < stage5.size()) {
            Triplet<String, TextColor, TextStyle> item = stage5.get(i);
            builder.append(Text.builder(item.getA()).color(item.getB()).style(item.getC()).build());
            i++;
        }

        builder.build();
    }

    @Test
    public void testBetterMethod() {
        // Input string
        final String input = "This &kis a &c&l[NOTICE]: &7The &nserver &7will rest&m&lart in approximately &6&o7 minutes&7. Please save your work!";

        String processed = "&f" + input;

        /**
         * "color code" refers only to formatting codes that handle color
         * "style code" refers only to formatting codes that handle style
         HELPFUL NOTES
         * A color code ignores all styles before it
         * If there are consecutive color codes with no breaks, only consider the last
         IDEA A
         * Input should be split up by presence of color codes
         * Within each group, split by presence of style codes
         * Prepend each subgroup with the style codes of the previous, unless the code is either: the same code, or '&r'
         * The color code should be prepended to each subgroup, unless the subgroup's style code is '&r' (reset)
         * For each group, parse the formatting codes, and create a text object
         *
         *
         * ((?:&[abcdef1234567890])+(?:&[^abcdef1234567890])*[^&]*)
         * ((&[abcdef])((?:)|())*)+
         * ((?:\&[a-f0-9])+(?:\&[^a-f0-9])*[^\&]*)
         * ((?:\&[a-f0-9])+((?:\&[^a-f0-9])*|[^\&]*)*)
         * ((?:\&[a-f0-9])+((?:\&[^a-f0-9]+)*|[^\&]*)*)
         *
         * (?:&[^a-f0-9]|.)
         * FINAL: (&[a-f0-9](&[g-z&]+|[^&]*)+)
         * FINAL #2: (&[a-f0-9](&[^a-f^0-9]+|[^&]*)+)
         * FINAL #3: (&[a-f0-9](&(?![a-f0-9])+|[^&]*)+)
         * FINAL #4: ((?:&[a-f0-9]|^(?:&(?![a-f0-9])+|[^&]*)+)(?:&(?![a-f0-9])+|[^&]*)+) matches no code at the beginning as well
         *
         * (&[k-n](&(?![k-n])+|[^&]*)+)


         */

        // Compile patterns
        Pattern colorPattern = Pattern.compile("(&[a-f0-9](&(?![a-f0-9])+|[^&]*)+)");
        Pattern stylePattern = Pattern.compile("(&[k-n](&(?![k-n])+|[^&]*)+)");
        Pattern codePattern = Pattern.compile("&(.)");

        // Create matcher for colors
        Matcher stage1 = colorPattern.matcher(processed);

        // Empty list of Text
        ArrayList<Text> texts = new ArrayList<>();

        // Iterate through the matches
        while (stage1.find()) {

            // Get color code
            final char color = stage1.group().charAt(1);

            // Get TextColor from code
            final TextColor textColor = TextColor.getColorFromChar(color);

            // Create matcher for styles
            Matcher stage2 = stylePattern.matcher(stage1.group().substring(2));

            // Remove all formatting codes
            final String preOutput = stage1.group().replaceAll("&.", "");

            // If the group doesn't have any style codes, simply add to the texts
            if (!stage2.find()) {
//                out.format("Pre: %s%n", preOutput);
                texts.add(Text.builder(preOutput).color(textColor).build());
                continue;
            }

            // Iterate through the matches
            while (stage2.find()) {

                // Get style codes
                Matcher stage3 = codePattern.matcher(stage2.group());

                Set<Character> styles = new HashSet<>();

                // Iterate through codes
                while (stage3.find()) {
                    // Get style char
                    final char style = stage3.group().charAt(1);

                    // Add to list of styles if not already in
                    styles.add(style);

//                    out.format("Stage3 Matched: %s", style);
                }

                /*for (Character style : styles) {
                    out.format("Style: %s%n", style);
                }*/


                // Remove char codes
                final String output = stage2.group().replaceAll("&.", "");
//                out.format("Output: %s%n", output);

                TextStyle textStyle = TextStyle.NONE;

                // Add to texts
                texts.add(Text.builder(output).color(textColor).style(textStyle).build());

            }
        }


       /* for (Text text : texts) {
            out.format("Text: %s, Color: %s, Style: %s%n", text.toFormat(), text.getFormat().getColor(), text.getFormat().getStyle());
        }*/

        Text.Builder text = Text.builder(texts.get(0));

        for (int i = 1; i < texts.size(); i++) {
            text.append(texts.get(i));
        }

//        out.format("Text: %s%n", text.build());


    }

    @Test
    public void aThirdWay() {
        // The input value
       final String input = "This &kis a &c&l[NOTICE]: &7The &nserver &7will rest&m&lart in approximately &6&o7 minutes&7. Please save your work!";
//        final String input = "&7";
        // Empty text array
        ArrayList<Text> texts = new ArrayList<>();

        // Compile patterns
        Pattern colorPattern = Pattern.compile("((?:&[a-f0-9]|^(?:&(?![a-f0-9])+|[^&]*)+)(?:&(?![a-f0-9])+|[^&]*)+)");
        Pattern stylePattern = Pattern.compile("((?:&[k-n]|^(?:&(?![k-n])+|[^&]*)+)(?:&(?![k-n])+|[^&]*)+)");

        // STAGE 1 - Match groups of color
        final Matcher stage1 = colorPattern.matcher(input);

        int i = 0;

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

            out.format("Stage1 Match #%d: %s, Color: %s%n", i++, match1, color);

            TextStyle textStyle = TextStyle.NONE;

            int j = 0;
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
                        break;
                }

                // TODO urls, actions

                // Add to texts
                texts.add(Text.builder(match2.replace("&.", "")).style(textStyle).color(textColor).build());

                out.format("\tStage2 Match #%d: %s Style: %s%n", j++, match2, style);
            }

            out.println(textStyle);

        }

        Text.Builder text = Text.builder(texts.get(0));

        for (int i1 = 1; i1 < texts.size(); i1++) {
            text.append(texts.get(i1));
        }

        text.build();
        out.println(new Gson().toJsonTree(text.build()));

    }

    @Test
    void testFinal() {
            System.out.println(TextHelper.legacyStringToText("This &kis a &c&l[NOTICE]: &7The &nserver &7will rest&m&lart in approximately &6&o7 minutes&7. Please save your work!"));
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(Text.builder("This is some RED text.").color(TextColor.RED).build(), Text.of("&cThis is some RED text."));

        Text b2 = Text.builder("This is some BOLD GOLD text.").color(TextColor.GOLD).style(new TextStyle().bold()).build();
        Text of2 = Text.of("&6&lThis is some BOLD GOLD text.");
        Assertions.assertEquals(b2, of2);

        Text b3 = Text.builder("This is some").color(TextColor.LIGHT_PURPLE).append(Text.builder(" multicolored text.").color(TextColor.RED).build()).build();
        Text of3 = Text.of("&dThis is some&c multicolored text.");
        Assertions.assertEquals(b3, of3);

        Text.of("&c&lThis is a &obunch of &4stuff &lthat should be format&cd");
    }

   // @Test
    void testFormat() {
        Assertions.assertEquals("&c&lThis is a &obunch of &4stuff &lthat should be format&cd", Text.of("&c&lThis is a &obunch of &4stuff &lthat should be format&cd").toFormat());
    }

    static boolean isColorChar(char c) {
        for (char color : colors) {
            if (c == color) return true;
        }
        return false;
    }
}
