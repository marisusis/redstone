package com.altarisnine.redstone.common.commands;

import com.altarisnine.redstone.api.command.CommandContext;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.command.argument.OfflinePlayerArgument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.OfflinePlayer;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

public class PlayerInfoCommand extends CommandNode {
    public PlayerInfoCommand() {
        super("info", Rank.TRAINEE, "Get information about a player", new OfflinePlayerArgument("player"));
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        OfflinePlayer player = context.<OfflinePlayer>getOne("player").get();

        sender.sendMessage(Text.of("&7Name: &6%s%n&7Rank: &6%s", player.getName(), player.getRank().toString()));
    }
}
