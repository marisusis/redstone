package com.altarisnine.networkcore.common.commands;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.command.*;
import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;

public class AnnounceCommand extends CommandNode {
    public AnnounceCommand() {
        super("announce", Rank.DEFAULT, "Broadcast a message to the server");
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        String message = CommandNode.requireRemaining(context, "You must provide a message!");
        Core.getApi().getServer().announce(Text.of("&b[&a!&b]: &r" + message));
    }
}
