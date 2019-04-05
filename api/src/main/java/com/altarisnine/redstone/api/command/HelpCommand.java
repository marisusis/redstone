package com.altarisnine.redstone.api.command;

import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

import java.util.Collections;
import java.util.Map;

/**
 * HelpCommand is a utility subcommand that should automatically be added to any command.
 * It provides a framework for help messages; developers should avoid creating their own help systems with CommandNodes
 */
public final class HelpCommand extends CommandNode {

    /**
     * Instantiates a new HelpCommand
     */
    public HelpCommand() {
        super("help", Rank.DEFAULT, "Help command");
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        if (getParent() == null) throw new IllegalStateException("You cannot use the /help subcommand globally, there is a separate class for that!");

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("&d%s: &f%s", getParent().getUsage(), getParent().getDescription()));

        // TODO iterate through subcommands (how deep?), excluding /help
        CommandNode parent = getParent();
        parent.getChildren().forEach((key, node) -> {
            if (!node.getClass().equals(HelpCommand.class)) {
                builder.append("\n");
                builder.append(String.format("&d%s: &f%s", node.getUsage(), node.getDescription()));
            }
        });

        sender.sendMessage(Text.of(builder.toString()));

    }

    @Override
    public void addChild(CommandNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, CommandNode> getChildren() {
        return Collections.emptyMap();
    }
}
