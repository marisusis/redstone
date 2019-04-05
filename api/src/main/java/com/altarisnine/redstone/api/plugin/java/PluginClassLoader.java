package com.altarisnine.redstone.api.plugin.java;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.plugin.InvalidPluginException;
import com.altarisnine.redstone.api.plugin.PluginInfo;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class PluginClassLoader extends URLClassLoader {
    private final JarFile jarfile;

    private final JavaPluginLoader loader;
    private final PluginInfo pluginInfo;
    private final File dataFolder;
    private final File file;

    @Getter JavaPlugin plugin;
    private JavaPlugin initializedPlugin;

    PluginClassLoader(final JavaPluginLoader loader, final ClassLoader parent, final PluginInfo pluginInfo, final File dataFolder, final File file) throws IOException, InvalidPluginException {
        super(new URL[]{file.toURI().toURL()}, parent);

        this.loader = loader;
        this.dataFolder = dataFolder;
        this.file = file;
        this.pluginInfo = pluginInfo;

        // Get the plugin's jarfile
        jarfile = new JarFile(file);
        try {
            Class<?> mainClass;
            try {
                // Get the plugin's main class
                mainClass = Class.forName(pluginInfo.getMainClass(), true, this);
            } catch (ClassNotFoundException e) {
                throw new InvalidPluginException("Unable to find main class", e);
            }

            Class<? extends JavaPlugin> pluginClass;
            try {
                pluginClass = mainClass.asSubclass(JavaPlugin.class);
            } catch (ClassCastException e) {
                throw new InvalidClassException("Main class " + pluginInfo.getMainClass() + " does not extend JavaPlugin");
            }

            this.plugin = pluginClass.newInstance();
        } catch (InstantiationException e) {
            throw new InvalidPluginException("Weird plugin class", e);
        } catch (IllegalAccessException e) {
            throw new InvalidPluginException("If you have a custom nullary constructor in your plugin class, please make sure it has a public visibility", e);
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            return super.findClass(name);
        } catch (ClassNotFoundException e) {
            // Use the loader from within the plugin's API, so static instances are the same
            return Redstone.getApi().getClass().getClassLoader().loadClass(name);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            jarfile.close();
        }
    }

    synchronized void init(JavaPlugin plugin) {
        if (this.plugin != null || this.initializedPlugin != null) {
            throw new IllegalArgumentException("That plugin is already initialized");
        }

        this.initializedPlugin = plugin;

        plugin.init(loader, loader.server, pluginInfo, dataFolder, file, this);
    }
}
