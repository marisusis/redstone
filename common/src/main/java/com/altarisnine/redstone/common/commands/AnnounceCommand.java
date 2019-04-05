package com.altarisnine.redstone.common.commands;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.command.*;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

public class AnnounceCommand extends CommandNode {
    public AnnounceCommand() {
        super("announce", Rank.DEFAULT, "Broadcast a message to the server");
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        String message = CommandNode.requireRemaining(context, "You must provide a message!");
        Redstone.getApi().getServer().announce(Text.of("&b[&a!&b]: &r" + message));
    }
}
