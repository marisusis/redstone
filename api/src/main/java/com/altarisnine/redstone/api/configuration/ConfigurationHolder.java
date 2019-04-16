package com.altarisnine.redstone.api.configuration;

import com.altarisnine.redstone.api.configuration.file.FileConfiguration;

import java.io.File;
import java.io.InputStream;

public interface ConfigurationHolder {
    File getDataFolder();
    boolean hasResource(String name);
    InputStream getResource(String name);
    String getIdentifier();
    FileConfiguration getConfig(String config);
}
