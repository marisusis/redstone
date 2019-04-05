package com.altarisnine.redstone.api.plugin;

import java.io.File;

public interface PluginLoader {
    Plugin loadPlugin(File file) throws InvalidPluginException;

    PluginInfo getPluginInfo(File file) throws InvalidPluginException;

    void enablePlugin(Plugin plugin);

    void disablePlugin(Plugin plugin);
}
