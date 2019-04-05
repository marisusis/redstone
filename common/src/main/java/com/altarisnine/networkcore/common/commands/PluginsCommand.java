package com.altarisnine.networkcore.common.commands;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.command.CommandContext;
import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.api.command.CommandSender;
import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.text.format.TextColor;
import com.altarisnine.networkcore.api.util.Rank;

public class PluginsCommand extends CommandNode {

    public PluginsCommand() {
        super("plugins", Rank.DEFAULT, "List all plugins");
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        Text.Builder builder = Text.builder("Plugins:").color(TextColor.AQUA);
        Core.getApi().getPluginManager().getPlugins().forEach(p -> {
            builder.append(Text.of(" "));
            builder.append(Text.builder(p.getName()).color(p.isEnabled() ? TextColor.GREEN : TextColor.RED).build());
        });
        sender.sendMessage(builder.build());
    }
}
