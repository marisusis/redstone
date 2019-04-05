package com.altarisnine.networkcore.bungee.bootstrap;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.bungee.command.BungeeConsoleCommandSender;
import com.altarisnine.networkcore.common.NetworkCore;
import lombok.Getter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;

public final class BungeeCommandBootstrap extends net.md_5.bungee.api.plugin.Command implements TabExecutor {
    private final NetworkCore core;

    @Getter private final CommandNode command;

    public BungeeCommandBootstrap(NetworkCore instance, CommandNode command) {
        super(command.getName(), command.getPermissionLevel().toString(), command.getAliases().toArray(new String[command.getAliases().size()]));
        this.core = instance;
        this.command = command;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) command.process(Core.getApi().getPlayer(((ProxiedPlayer) commandSender).getUniqueId()), new ArrayList<>(Arrays.asList(strings)));
        else command.process(new BungeeConsoleCommandSender(commandSender), new ArrayList<>(Arrays.asList(strings)));
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] strings) {
        core.getLogger().error(ArrayUtils.toString(strings));
        return Arrays.asList("bukkit", "bungee", "spigot", "sponge");
    }
}
