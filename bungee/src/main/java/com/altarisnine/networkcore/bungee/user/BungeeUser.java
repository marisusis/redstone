package com.altarisnine.networkcore.bungee.user;

import com.altarisnine.networkcore.common.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class BungeeUser implements User {
    private final CommandSender sender;

    public BungeeUser(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendMessage(String message) {
        sender.sendMessage(TextComponent.fromLegacyText(
                ChatColor.translateAlternateColorCodes('&', message)
        ));
    }

    @Override
    public boolean isPlayer() {
        return (sender instanceof ProxiedPlayer);
    }

    @Override
    public UUID getUniqueId() {
        return isPlayer() ? ((ProxiedPlayer) sender).getUniqueId() : null;
    }

    @Override
    public String getName() {
        return sender.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
