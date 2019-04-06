package com.altarisnine.redstone.bungee.commands;

import com.altarisnine.redstone.api.command.CommandContext;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.command.argument.Argument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

import java.util.Arrays;
import java.util.List;

public class LeaveCommand extends CommandNode {
    public LeaveCommand() {
        super("leave", Rank.DEFAULT, "Travel to the main lobby", Arrays.asList("lobby"));
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        Player player = requirePlayer(sender);

        requireFalse(player.getServerName().equals("hub"), "You are in the hub already!");

        player.sendToServer("hub");
        player.sendMessage(Text.of("&9Sending you to the lobby..."));
    }
}
