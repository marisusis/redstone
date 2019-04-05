package com.altarisnine.networkcore.api.plugin.java;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.configuration.file.FileConfiguration;
import com.altarisnine.networkcore.api.plugin.Plugin;
import com.altarisnine.networkcore.api.plugin.PluginInfo;
import com.altarisnine.networkcore.api.plugin.PluginLoader;
import com.altarisnine.networkcore.api.server.Server;
import com.altarisnine.networkcore.api.util.Logger;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public abstract class JavaPlugin implements Plugin {
    @Getter private PluginLoader loader;
    @Getter private Server server;
    private File dataFolder;
    @Getter private PluginInfo pluginInfo;
    private boolean enabled = false;
    private File file;
    private ClassLoader classLoader;

    public JavaPlugin() {
        Core.getApi().getLogger().debug("Nullary JavaPlugin constructor");
        ClassLoader classLoader = this.getClass().getClassLoader();
        if (!(classLoader instanceof PluginClassLoader)) {
            throw new IllegalStateException("Can only be initialized through a PluginClassLoader");
        }
        ((PluginClassLoader) classLoader).init(this);
    }

    protected JavaPlugin(final JavaPluginLoader loader, final PluginInfo info, final File dataFolder) {
        this.pluginInfo = info;
        this.dataFolder = dataFolder;
    }

    @Override
    public final FileConfiguration getConfig(String name) {
        return Core.getApi().getConfigurationManager().getConfig(name, this);
    }

    @Override
    public InputStream getResource(String name) {
        URL url = getClass().getClassLoader().getResource(name);
        if (url == null) return null;
        else {
            try {
                URLConnection connection = url.openConnection();
                connection.setUseCaches(false);
                return connection.getInputStream();
            } catch (IOException e) {
                return null;
            }
        }
    }

    void setEnabled(boolean enabled) {
        if (enabled != this.enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }

    void init(PluginLoader loader, Server server, PluginInfo pluginInfo, File dataFolder, File file, ClassLoader classLoader) {
        this.loader = loader;
        this.server = server;
        this.dataFolder = dataFolder;
        this.pluginInfo = pluginInfo;
        this.file = file;
        this.classLoader = classLoader;
    }

    @Override
    public boolean hasResource(String name) {
        return (getClass().getClassLoader().getResource(name) != null);
    }

    @Override
    public final Logger getLogger() {
        return new Logger() {
            @Override
            public void error(String message) {
                Core.getApi().getLogger().error("(" + getName() + "): " + message);
            }

            @Override
            public void debug(String message) {
                Core.getApi().getLogger().debug("(" + getName() + "): " + message);
            }

            @Override
            public void info(String message) {
                Core.getApi().getLogger().info("(" + getName() + "): " + message);
            }
        };
    }

    @Override
    public String getName() {
        return pluginInfo.getName();
    }

    @Override
    public String getVersion() {
        return pluginInfo.getVersion();
    }

    @Override
    public String getDescription() {
        return pluginInfo.getDescription();
    }

    @Override
    public final boolean isEnabled() {
        return enabled;
    }

    @Override
    public File getDataFolder() {
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        return dataFolder;
    }
}
