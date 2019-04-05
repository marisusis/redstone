package com.altarisnine.redstone.common.commands.guard;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.command.CommandContext;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.command.argument.PlayerArgument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.session.Session;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

public class FreezeCommand extends CommandNode {
    public FreezeCommand() {
        super("freeze", Rank.SRMOD, "Freeze a player", new PlayerArgument("player"));
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        Player player = context.<Player>getOne("player").get();

        Session session = Redstone.getApi().getGuard().getSession(player.getUniqueId());

        session.setInLockdown(true);
        player.sendMessage(Text.of("&cYou are now frozen."));
    }
}
