package com.altarisnine.networkcore.common.commands.guard;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.command.*;
import com.altarisnine.networkcore.api.command.argument.FlagArgument;
import com.altarisnine.networkcore.api.command.argument.PlayerArgument;
import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.flag.Flag;
import com.altarisnine.networkcore.api.guard.session.Session;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;
import com.altarisnine.networkcore.common.NetworkCore;

import java.util.Optional;

public class GuardBaseCommand extends CommandNode {
    private final NetworkCore core;

    public GuardBaseCommand(NetworkCore core) {
        super("guard", Rank.ADMIN, "Base guard command");

        this.core = core;

        addChild(Command.builder("override")
                .description("Set a global flag override for a player")
                .alias("o")
                .argument(new PlayerArgument("player"))
                .argument(new FlagArgument("flag"))
                .executor((sender, context) -> {
                    Player player = context.<Player>getOne("player").get();
                    Flag<?> flag = context.<Flag<?>>getOne("flag").get();

                    Session session = core.getSessionManager().getSession(player.getUniqueId());

                    session.setOverride(flag);
                    player.sendMessage(Text.of("&6Set override &c%s &6for &c%s&6.", flag.getName(), player.getDisplayName()));
                })
                .build());

        addChild(Command.builder("unset")
                .description("Unset overrides for a player")
                .alias("u")
                .argument(new PlayerArgument("player"))
                .argument(new FlagArgument("flag", false))
                .executor((sender, context) -> {
                    Player player = context.<Player>getOne("player").get();
                    Optional<Flag<?>> flagOpt = context.getOne("flag");

                    // Get player's session
                    Session session = core.getSessionManager().getSession(player.getUniqueId());

                    if (flagOpt.isPresent()) {
                        Flag<?> flag = flagOpt.get();
                        session.unsetOverride(flag);
                        player.sendMessage(Text.of("&6Unset override &c%s &6for &c%s&6.", flag.getName(), player.getDisplayName()));
                    } else {
                        session.unsetAllOverrides();
                        player.sendMessage(Text.of("&6Cleared all overrides for &c%s&6.", player.getDisplayName()));
                    }

                })
                .build());
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        sender.sendMessage(Text.of("%s Region(s) loaded.", Core.getApi().getGuard().getRegionNames().size()));
    }
}
