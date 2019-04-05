package com.altarisnine.redstone.api.plugin;

import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.event.Listener;

import java.io.File;
import java.util.Set;

public interface PluginManager {
    Plugin[] loadPlugins(File directory);

    Set<Plugin> getPlugins();

    void registerListener(Listener listener, Plugin plugin);
    void registerCommand(CommandNode command, Plugin plugin);
}
