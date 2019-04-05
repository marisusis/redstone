package com.altarisnine.networkcore.common.commands;

import com.altarisnine.networkcore.api.command.CommandContext;
import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.api.command.CommandSender;
import com.altarisnine.networkcore.api.command.argument.PlayerArgument;
import com.altarisnine.networkcore.api.command.argument.RankArgument;
import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;

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
