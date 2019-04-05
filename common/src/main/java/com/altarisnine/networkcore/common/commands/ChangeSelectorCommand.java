package com.altarisnine.networkcore.common.commands;

import com.altarisnine.networkcore.api.command.CommandContext;
import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.api.command.CommandSender;
import com.altarisnine.networkcore.api.command.argument.Argument;
import com.altarisnine.networkcore.api.command.argument.ChoiceArgument;
import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.spatial.selection.ColumnSelector;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;

public class ChangeSelectorCommand extends CommandNode {
    public ChangeSelectorCommand(String name, Rank permissionLevel, Argument<?>... arguments) {
        super("selector", Rank.DEFAULT, "Change your current wand selector", new ChoiceArgument("type", "column"));
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        Player player = CommandNode.requirePlayer(sender);

        switch (context.<String>getOne("type").get()) {
            case "column":
                player.changeSelectorType(ColumnSelector.class);
                player.sendMessage(Text.of("&6You are now using the &e%s", player.getSelector().getClass().getName()));
                break;
            default:
                player.sendMessage(Text.of("&6You are currently using the &e%s", player.getSelector().getClass().getName()));
                break;
        }
    }
}
