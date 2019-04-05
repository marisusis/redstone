package com.altarisnine.redstone.common.commands;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.command.CommandContext;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.text.format.TextColor;
import com.altarisnine.redstone.api.util.Rank;

public class PluginsCommand extends CommandNode {

    public PluginsCommand() {
        super("plugins", Rank.DEFAULT, "List all plugins");
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        Text.Builder builder = Text.builder("Plugins:").color(TextColor.AQUA);
        Redstone.getApi().getPluginManager().getPlugins().forEach(p -> {
            builder.append(Text.of(" "));
            builder.append(Text.builder(p.getName()).color(p.isEnabled() ? TextColor.GREEN : TextColor.RED).build());
        });
        sender.sendMessage(builder.build());
    }
}
