package com.altarisnine.networkcore.bukkit.bootstrap;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.bukkit.command.BukkitConsoleCommandSender;
import io.sentry.Sentry;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class BukkitCommandBootstrap extends BukkitCommand {
    @Getter private final CommandNode command;

    public BukkitCommandBootstrap(CommandNode command) {
        super(command.getName(), command.getDescription(), "SHOULD BE DONE PROGRAMMATICALLY", command.getAliases());
        this.command = command;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try {
            if (sender instanceof Player)
                command.process(Core.getApi().getPlayer(((Player) sender).getUniqueId()), new ArrayList<>(Arrays.asList(args)));
            else
                command.process(new BukkitConsoleCommandSender((ConsoleCommandSender) sender) /* FIXME change to getter? */, new ArrayList<>(Arrays.asList(args)));
        } catch (Exception e) {
            Event evt = new EventBuilder()
                    .withMessage("Error executing command /" + commandLabel + " through bukkit")
                    .withSentryInterface(new ExceptionInterface(e))
                    .withTag("command", commandLabel)
                    .withExtra("label", commandLabel)
                    .withExtra("args", Arrays.toString(args))
                    .build();

            Sentry.capture(evt);
        }
        return true;
    }
}
