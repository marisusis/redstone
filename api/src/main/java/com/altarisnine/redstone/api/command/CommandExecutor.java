package com.altarisnine.redstone.api.command;

import com.altarisnine.redstone.api.command.exception.InternalCommandException;

public interface CommandExecutor {
    void execute(CommandSender sender, CommandContext context) throws InternalCommandException;
}
