package com.altarisnine.networkcore.api.command;

import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;

import java.util.Collections;
import java.util.Map;

public final class GlobalHelpCommand extends CommandNode {

    public GlobalHelpCommand() {
        super("help", Rank.DEFAULT, "Help command");
    }

    @Override
    public void addChild(CommandNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        sender.sendMessage(Text.of("&c/&6h&ee&al&bp &dc&5o&9m&1m&0a&8n&7d&f!"));
    }

    @Override
    public Map<String, CommandNode> getChildren() {
        return Collections.emptyMap();
    }

    @Override
    public void setParent(CommandNode parent) {
        throw new UnsupportedOperationException();
    }
}
