package com.altarisnine.redstone.bukkit.command;

import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.bukkit.bootstrap.BukkitCommandBootstrap;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.command.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

public final class BukkitCommandManager extends CommandManager {

    public BukkitCommandManager(RedstoneCore instance) {
        super(instance);
    }

    @Override
    protected void register(CommandNode command) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(command.getName(), new BukkitCommandBootstrap(command));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
