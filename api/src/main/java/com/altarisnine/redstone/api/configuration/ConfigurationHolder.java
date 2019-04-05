package com.altarisnine.redstone.api.configuration;

import java.io.File;
import java.io.InputStream;

public interface ConfigurationHolder {
    File getDataFolder();
    boolean hasResource(String name);
    InputStream getResource(String name);
}
