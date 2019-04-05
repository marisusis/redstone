package com.altarisnine.networkcore.common.configuration;

import com.altarisnine.networkcore.api.configuration.ConfigurationHolder;
import com.altarisnine.networkcore.api.configuration.ConfigurationManager;
import com.altarisnine.networkcore.api.configuration.InvalidConfigurationException;
import com.altarisnine.networkcore.api.configuration.file.FileConfiguration;
import com.altarisnine.networkcore.api.configuration.file.YamlConfiguration;
import com.altarisnine.networkcore.common.NetworkCore;

import java.io.*;

public final class CoreConfigurationManager implements ConfigurationManager {
    private final NetworkCore plugin;

    public CoreConfigurationManager(NetworkCore instance) {
        plugin = instance;
    }

    @Override
    public FileConfiguration getConfig(String name) {
        return getConfig(name, plugin);
    }

    @Override
    public FileConfiguration getConfig(String name, ConfigurationHolder holder) {
        File configFile = new File(holder.getDataFolder(), name);

        FileConfiguration config = new YamlConfiguration();

        try {
            if (configFile.exists()) config.load(configFile);
            else {
                if (holder.hasResource(name)) {
                    // Copy defaults
                    copyResource(name, false, holder);
                } else {
                    // Create new file
                    configFile.createNewFile();
                }

                // Load file into config
                config.load(configFile);
            }
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return config;
    }

    @Override
    public void copyResource(String name, boolean replace) {
        copyResource(name, replace, plugin);
    }

    @Override
    public void copyResource(String name, boolean replace, ConfigurationHolder holder) {
        InputStream input = holder.getResource(name);
        if (input == null) throw new IllegalStateException("Cannot find resource to copy!");
        else {
            File targetFile = new File(holder.getDataFolder(), name);
            if (targetFile.exists() && !replace) {
                throw new IllegalStateException("File already exists!");
            } else {
                try {
                    OutputStream out = new FileOutputStream(targetFile);
                    byte[] buf = new byte[1024];

                    int len;
                    while ((len = input.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    out.close();
                    input.close();
                } catch (IOException e) {
                    throw new IllegalStateException("An error occurred while trying to copy defaults for " + name + " in " + holder.getDataFolder().toString());
                }
            }

        }
    }
}
