package com.altarisnine.redstone.common.commands;

import com.altarisnine.redstone.api.command.CommandContext;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.command.argument.PlayerArgument;
import com.altarisnine.redstone.api.command.argument.RankArgument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

public class RankCommand extends CommandNode {

    public RankCommand() {
        super("rank", Rank.ADMIN, "Modify a player's rank", new PlayerArgument("player"), new RankArgument("group"));
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        // Get target player and group
        Player target = context.<Player>getOne("player").get();
        Rank rank = context.<Rank>getOne("group").get();

        // Apply group to target player
        target.setRank(rank);

        sender.sendMessage(Text.of("&6Set player &c%s\'s &6rank to &c%s&6.", target.getName(), rank.toString()));
    }
}
