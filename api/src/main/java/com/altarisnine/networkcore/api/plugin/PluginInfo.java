package com.altarisnine.networkcore.api.plugin;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

public final class PluginInfo {

    private static final Yaml yaml = new Yaml();

    @Getter private final String name;
    @Getter private final String mainClass;
    @Getter private final String version;
    @Getter private final String description;

    public PluginInfo(InputStream stream) {
        this((Map<String, String>) yaml.load(stream));
    }

    public PluginInfo(Reader reader) {
        this((Map<String, String>) yaml.load(reader));
    }

    public PluginInfo(Map<String, String> values) {
        this(values.get("name"), values.get("mainClass"), values.get("version"), values.get("description"));
    }

    public PluginInfo(String name, String mainClass, String version, String description) {
        this.name = name;
        this.mainClass = mainClass;
        this.version = version;
        this.description = description;
    }

    @Override
    public String toString() {
        return "PluginInfo{" +
                "name='" + name + '\'' +
                ", mainClass='" + mainClass + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
