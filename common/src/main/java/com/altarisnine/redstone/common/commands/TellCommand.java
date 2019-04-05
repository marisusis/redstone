package com.altarisnine.redstone.common.commands;

import com.altarisnine.redstone.api.command.*;
import com.altarisnine.redstone.api.command.argument.PlayerArgument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

public class TellCommand extends CommandNode {
    public TellCommand() {
        super("tell", Rank.DEFAULT, "Send a private mesage", new PlayerArgument("playername"));
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        Player source = CommandNode.requirePlayer(sender);
        String message = CommandNode.requireRemaining(context, "You must provide a message!");

        Player player = context.<Player>getOne("player").get();

        player.sendMessage(Text.of("%s &8to you: &7%s", source.getDisplayName(), message));
        source.sendMessage(Text.of("&8You to %s: &7%s", player.getDisplayName(), message));
    }
}
