package com.altarisnine.redstone.api.plugin;

import com.altarisnine.redstone.api.configuration.ConfigurationHolder;
import com.altarisnine.redstone.api.configuration.file.FileConfiguration;
import com.altarisnine.redstone.api.util.Logger;

import java.io.InputStream;

public interface Plugin extends ConfigurationHolder {
    String getName();

    String getVersion();

    String getDescription();

    void onEnable();

    void onDisable();

    boolean isEnabled();

    Logger getLogger();

    InputStream getResource(String name);

    boolean hasResource(String name);

    FileConfiguration getConfig(String name);

}
