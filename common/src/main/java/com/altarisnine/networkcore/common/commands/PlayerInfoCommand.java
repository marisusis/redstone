package com.altarisnine.networkcore.common.commands;

import com.altarisnine.networkcore.api.command.CommandContext;
import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.api.command.CommandSender;
import com.altarisnine.networkcore.api.command.argument.OfflinePlayerArgument;
import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.entity.living.player.OfflinePlayer;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;

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
