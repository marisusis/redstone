package com.altarisnine.networkcore.bukkit.user;

import com.altarisnine.networkcore.common.user.User;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitUser implements User {
    private final CommandSender sender;

    @Getter private final UUID uniqueId;

    public BukkitUser(CommandSender sender) {
        this.sender = sender;

        // Store UUID in memory to prevent null reference to command sender when player leaves
        this.uniqueId = isPlayer() ? ((Player) sender).getUniqueId() : null;
    }

    public void sendMessage(String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public boolean isPlayer() {
        return (sender instanceof Player);
    }

    public String getName() {
        return sender.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
