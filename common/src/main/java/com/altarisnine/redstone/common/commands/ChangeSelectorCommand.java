package com.altarisnine.redstone.common.commands;

import com.altarisnine.redstone.api.command.CommandContext;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.command.argument.Argument;
import com.altarisnine.redstone.api.command.argument.ChoiceArgument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.guard.spatial.selection.ColumnSelector;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

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
