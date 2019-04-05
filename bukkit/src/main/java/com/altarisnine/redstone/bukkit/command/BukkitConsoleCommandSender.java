package com.altarisnine.redstone.bukkit.command;

import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;
import com.altarisnine.redstone.bukkit.Converter;
import org.bukkit.command.ConsoleCommandSender;

public class BukkitConsoleCommandSender implements CommandSender {
    private final ConsoleCommandSender sender;

    public BukkitConsoleCommandSender(ConsoleCommandSender sender) {
        this.sender = sender;
    }

    @Override
    public boolean hasClearance(Rank rank) {
        return true;
    }

    @Override
    public void sendMessage(String message) {
        sendMessage(Text.of(message));
    }

    @Override
    public void sendMessage(Text text) {
        sender.spigot().sendMessage(Converter.parse(text));
    }
}
