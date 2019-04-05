package com.altarisnine.redstone.api.plugin.java;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.plugin.InvalidPluginException;
import com.altarisnine.redstone.api.plugin.Plugin;
import com.altarisnine.redstone.api.plugin.PluginInfo;
import com.altarisnine.redstone.api.plugin.PluginLoader;
import com.altarisnine.redstone.api.server.Server;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JavaPluginLoader implements PluginLoader {
    private final Collection<Class<?>> pluginClasses = new ArrayList<>();

    @Getter private final List<PluginClassLoader> loaders = new CopyOnWriteArrayList<>();

    final Server server;

    public JavaPluginLoader(Server server) {
        this.server = server;
    }

    @Override
    public Plugin loadPlugin(File file) throws InvalidPluginException {
        // Check if the file exists
        if (!file.exists()) {
            throw new InvalidPluginException(new FileNotFoundException("That plugin does not exist!"));
        }

        JavaPlugin plugin = null;

        // Get plugin info
        PluginInfo info = getPluginInfo(file);

        Redstone.getApi().getLogger().debug("Attempting to load plugin [" + info.getName() + "] from file");

        // Create the plugin loader
        PluginClassLoader loader;

        try {
            loader = new PluginClassLoader(this, getClass().getClassLoader(), info, new File(file.getParentFile(), info.getName()), file);
        } catch (InvalidPluginException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidPluginException(e);
        }

        loaders.add(loader);

        // Get loaded plugin from loader
        return loader.plugin;
    }

    @Override
    public PluginInfo getPluginInfo(File file) throws InvalidPluginException {
        InputStream stream = null;
        JarFile jar = null;

        try {
            // Get jar file and info file
            jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("info.yml");

            // Create input stream
            stream = jar.getInputStream(entry);

            // Create and return plugin info
            return new PluginInfo(stream);
        } catch (IOException e) {
            throw new InvalidPluginException("An error occurred while trying to load the plugin's information file. Please make sure the info.yml file is included in the jarfile and is valid.", e);
        } finally {
            try {
                if (jar != null) {
                    jar.close();
                }
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void enablePlugin(Plugin plugin) {
        if (!plugin.isEnabled()) {

        }
    }

    @Override
    public void disablePlugin(Plugin plugin) {

    }
}
