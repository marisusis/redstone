package com.altarisnine.networkcore.common.commands.guard;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.command.CommandContext;
import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.api.command.CommandSender;
import com.altarisnine.networkcore.api.command.argument.PlayerArgument;
import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.session.Session;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;

public class FreezeCommand extends CommandNode {
    public FreezeCommand() {
        super("freeze", Rank.SRMOD, "Freeze a player", new PlayerArgument("player"));
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        Player player = context.<Player>getOne("player").get();

        Session session = Core.getApi().getGuard().getSession(player.getUniqueId());

        session.setInLockdown(true);
        player.sendMessage(Text.of("&cYou are now frozen."));
    }
}
