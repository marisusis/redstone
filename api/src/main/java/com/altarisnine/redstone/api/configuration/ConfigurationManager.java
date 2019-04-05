package com.altarisnine.redstone.api.configuration;

import com.altarisnine.redstone.api.configuration.file.FileConfiguration;

public interface ConfigurationManager {
    FileConfiguration getConfig(String name);

    FileConfiguration getConfig(String name, ConfigurationHolder holder);

    void copyResource(String name, boolean replace);

    void copyResource(String name, boolean replace, ConfigurationHolder holder);

}
