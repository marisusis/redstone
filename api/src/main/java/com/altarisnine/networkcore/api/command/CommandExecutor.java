package com.altarisnine.networkcore.api.command;

import com.altarisnine.networkcore.api.command.exception.InternalCommandException;

public interface CommandExecutor {
    void execute(CommandSender sender, CommandContext context) throws InternalCommandException;
}
