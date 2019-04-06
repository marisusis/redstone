package com.altarisnine.redstone.bungee;

import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.text.format.TextColor;
import com.altarisnine.redstone.api.text.format.TextFormat;
import com.altarisnine.redstone.api.text.format.TextStyle;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class Converter {
    public static ChatColor color(TextColor color) {
        return ChatColor.valueOf(color.getColor().toString());
    }

    public static TextComponent parse(Text text) {
        TextComponent message = new TextComponent(text.getContent());

        // Get the text format
        TextFormat format = text.getFormat();

        // Set the color of the text component
        message.setColor(color(text.getFormat().getColor()));

        // Set the text style
        TextStyle style = format.getStyle();
        message.setBold(style.isBold());
        message.setItalic(style.isItalic());
        message.setUnderlined(style.isUnderline());
        message.setStrikethrough(style.isStrikethrough());
        message.setObfuscated(style.isMagic());

        if (text.getChildren() != null) {
            for (Text child : text.getChildren()) {
                message.addExtra(parse(child));
            }
        }

        //TEMP message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("hello").color(ChatColor.LIGHT_PURPLE).create()));

        return message;
    }
}
