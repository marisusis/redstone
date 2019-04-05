package com.altarisnine.networkcore.common.plugin;

import com.altarisnine.networkcore.api.command.CommandNode;
import com.altarisnine.networkcore.api.command.exception.InvalidCommandException;
import com.altarisnine.networkcore.api.event.Listener;
import com.altarisnine.networkcore.api.plugin.InvalidPluginException;
import com.altarisnine.networkcore.api.plugin.Plugin;
import com.altarisnine.networkcore.api.plugin.PluginLoader;
import com.altarisnine.networkcore.api.plugin.PluginManager;
import com.altarisnine.networkcore.api.plugin.java.JavaPluginLoader;
import com.altarisnine.networkcore.common.NetworkCore;
import io.sentry.Sentry;
import io.sentry.event.EventBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CorePluginManager implements PluginManager {
    private NetworkCore core;

    private final JavaPluginLoader loader;

    public CorePluginManager(NetworkCore instance) {
        core = instance;
        this.loader = new JavaPluginLoader(instance.getServer());
    }

    @Override
    public Plugin[] loadPlugins(File directory) {
        List<Plugin> result = new ArrayList<>();

        if (directory.exists()) {
            core.getLogger().info("Plugin dir exists!");
            for (File file : directory.listFiles()) {
                //if (file.getName().matches("(\\.*)\\.jar")) {
                    core.getLogger().info("Attempting to load core at " + file.getName());
                    Plugin plugin = loadPlugin(file);
                    if (plugin != null) {
                        result.add(plugin);
                        core.getLogger().info("Successfully loaded core at " + file.getName());
                    }
                //}
            }
        } else {
            core.getLogger().info("Plugin dir not found, creating.");
            directory.mkdir();
        }

        return result.toArray(new Plugin[0]);
    }

    @Override
    public Set<Plugin> getPlugins() {
        return loader.getLoaders().stream().map(loader -> loader.getPlugin()).collect(Collectors.toSet());
    }

    public synchronized Plugin loadPlugin(File file) {
        Plugin result = null;

        PluginLoader loader = new JavaPluginLoader(core.getServer());

        try {
            result = loader.loadPlugin(file);
        } catch (InvalidPluginException e) {
            return null;
        }

        return result;
    }

    @Override
    public void registerListener(Listener listener, Plugin plugin) {
        this.core.getEventManager().registerHandlers(listener, plugin);
    }

    @Override
    public void registerCommand(CommandNode command, Plugin plugin) {
        try {
            this.core.getCommandManager().registerCommand(command, plugin);
        } catch(InvalidCommandException e) {
            Sentry.capture(new EventBuilder()
                .withTag("command", command.getName())
                .withMessage("Unable to register command /" + command.getName())
                .withTag("plugin", plugin.getName()));

            this.core.getLogger().error("Unable to load command [" + command.getName() + "] from core [" + plugin.getName() + ']');
        }
    }
}
