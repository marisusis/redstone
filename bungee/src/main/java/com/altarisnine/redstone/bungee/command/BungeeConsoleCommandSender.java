package com.altarisnine.redstone.bungee.command;

import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;
import net.md_5.bungee.api.chat.TextComponent;

public class BungeeConsoleCommandSender implements CommandSender {

    private final net.md_5.bungee.api.CommandSender sender;

    public BungeeConsoleCommandSender(net.md_5.bungee.api.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public boolean hasClearance(Rank rank) {
        return true;
    }

    @Override
    public void sendMessage(String message) {
        sender.sendMessage(TextComponent.fromLegacyText(message));
    }

    @Override
    public void sendMessage(Text text) {
        throw new UnsupportedOperationException("not implemented!");
    }
}
