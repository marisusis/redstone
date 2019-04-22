package com.altarisnine.redstone.common.commands;

import com.altarisnine.redstone.api.command.CommandContext;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.util.Rank;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.guis.ServerGUI;

import java.util.Collections;

public class ServerCommand extends CommandNode {
    private RedstoneCore core;

    public ServerCommand(RedstoneCore core) {
        super("server", Rank.DEFAULT, "Change servers", Collections.singletonList("servers"));
        this.core = core;
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        Player player = requirePlayer(sender);

        core.getGuiManager().openGUI(player, new ServerGUI());
    }
}
