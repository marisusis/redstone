package com.altarisnine.networkcore.api.plugin;

import lombok.Getter;

public final class LoadedPlugin {
    @Getter private final Plugin plugin;
    @Getter private final String version;
    @Getter private final String name;

    public LoadedPlugin(CorePlugin anno, Plugin plugin) {
        this.plugin = plugin;
        this.version = anno.version();
        this.name = anno.name();
    }
}
