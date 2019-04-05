package com.altarisnine.networkcore.api.plugin;

import com.altarisnine.networkcore.api.configuration.ConfigurationHolder;
import com.altarisnine.networkcore.api.configuration.file.FileConfiguration;
import com.altarisnine.networkcore.api.util.Logger;

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
