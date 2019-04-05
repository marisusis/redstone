package com.altarisnine.redstone.bungee.bootstrap;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.bungee.command.BungeeConsoleCommandSender;
import com.altarisnine.redstone.common.RedstoneCore;
import lombok.Getter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;

public final class BungeeCommandBootstrap extends net.md_5.bungee.api.plugin.Command implements TabExecutor {
    private final RedstoneCore core;

    @Getter private final CommandNode command;

    public BungeeCommandBootstrap(RedstoneCore instance, CommandNode command) {
        super(command.getName(), command.getPermissionLevel().toString(), command.getAliases().toArray(new String[command.getAliases().size()]));
        this.core = instance;
        this.command = command;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) command.process(Redstone.getApi().getPlayer(((ProxiedPlayer) commandSender).getUniqueId()), new ArrayList<>(Arrays.asList(strings)));
        else command.process(new BungeeConsoleCommandSender(commandSender), new ArrayList<>(Arrays.asList(strings)));
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] strings) {
        core.getLogger().error(ArrayUtils.toString(strings));
        return Arrays.asList("bukkit", "bungee", "spigot", "sponge");
    }
}
