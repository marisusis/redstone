package com.altarisnine.redstone.sponge.user;

import com.altarisnine.redstone.common.user.User;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.UUID;

public class SpongeUser implements User {
    private CommandSource source;

    public SpongeUser(CommandSource source) {
        this.source = source;
    }

    public void sendMessage(String message) {
        source.sendMessage(Text.of(message));
    }

    public boolean isPlayer() {
        return (source instanceof Player);
    }

    public UUID getUniqueId() {
        return isPlayer() ? ((Player) source).getUniqueId() : null;
    }

    public String getName() {
        return source.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return source.hasPermission(permission);
    }
}
