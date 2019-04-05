package com.altarisnine.redstone.api.configuration.objects;

import com.altarisnine.redstone.api.configuration.Configuration;
import com.altarisnine.redstone.api.configuration.ConfigurationSection;
import com.altarisnine.redstone.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.redstone.api.configuration.serialization.ConfigSerialization;

import java.util.*;

public class ConfigurationSectionImpl implements ConfigurationSection {
    private final Map<String, Object> map = new HashMap<>();
    private final Configuration root;
    private final ConfigurationSection parent;
    private final String path;

    private static String SEPARATOR = ".";


    ConfigurationSectionImpl() {
        if (!(this instanceof Configuration)) {
            throw new IllegalStateException("Cannot instantiate root ConfigurationSection");
        }
        path = "";
        parent = null;
        root = (Configuration) this;
    }

    private ConfigurationSectionImpl(ConfigurationSection parent, String path) {
        this.path = path;
        this.root = parent.getRoot();
        this.parent = parent;

    }

    @Override
    public String getName() {
        return path;
    }

    @Override
    public <T> void set(String path, T obj) {
        // Check if the object is a primative type
        Class clazz = obj.getClass();

        if (isPrimative(obj)) {
            // It's a primative type, just add to the map of values
            map.put(path, obj);
        } else {
            // Check if the object is a ConfigSerializable
            if (obj instanceof ConfigSerializable) {
                map.put(path, ((ConfigSerializable) obj).serialize());
            } else {
                map.put(path, obj.toString());
            }
        }
    }

    // TODO nested section creation
    @Override
    public ConfigurationSection createSection(String path) {
        ConfigurationSection newSection = new ConfigurationSectionImpl(this, path);
        map.put(path, newSection);
        return newSection;
    }

    @Override
    public <T extends ConfigSerializable> T get(Class<T> clazz, String path) {
        return ConfigSerialization.deserializeWithMethod(getSection(path), clazz);
    }

    @Override
    public <T extends Enum> T getEnum(Class<T> clazz, String path) {
        return (T) Enum.valueOf(clazz, getString(path).toUpperCase());
    }

    @Override
    public int getInteger(String path) {
        Object obj = map.get(path);
        return (obj instanceof Integer) ? Integer.parseInt(String.valueOf(obj)) : null;
    }

    @Override
    public boolean getBoolean(String path) {
        Object obj = map.get(path);
        return (obj instanceof Boolean) ? Boolean.parseBoolean(String.valueOf(obj)) : null;
    }

    @Override
    public double getDouble(String path) {
        Object obj = map.get(path);
        return (obj instanceof Double) ? Double.parseDouble(String.valueOf(obj)) : null;
    }

    @Override
    public float getFloat(String path) {
        Object obj = map.get(path);
        return (obj instanceof Float) ? Float.parseFloat(String.valueOf(obj)) : null;
    }

    @Override
    public UUID getUUID(String path) {
        String s = getString(path);
        try {
            return UUID.fromString(s);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String getString(String path) {
        return String.valueOf(map.get(path));
    }

    @Override
    public Map<String, Object> getValues(boolean deep) {
        Map<String, Object> result = new LinkedHashMap<>();

        Configuration root = this.root;

        mapChildrenValues(result, this, deep);

        return result;
    }

    private void mapChildrenValues(Map<String, Object> output, ConfigurationSection section, boolean deep) {
        if (section instanceof ConfigurationSectionImpl) {
            ConfigurationSectionImpl sec = (ConfigurationSectionImpl) section;

            for (Map.Entry<String, Object> entry : sec.map.entrySet()) {
                output.put(createPath(section, entry.getKey(), this), entry.getValue());

                if (entry.getValue() instanceof ConfigurationSection) {
                    if (deep) {
                        mapChildrenValues(output, (ConfigurationSection) entry.getValue(), true);
                    }
                }
            }
        } else {
            Map<String, Object> values = section.getValues(deep);

            for (Map.Entry<String, Object> entry : values.entrySet()) {
                output.put(createPath(section, entry.getKey(), this), entry.getValue());
            }
        }
    }

    public static String createPath(ConfigurationSection section, String key) {
        return createPath(Objects.requireNonNull(section), key, (section == null) ? null : section.getRoot());
    }

    private static String createPath(ConfigurationSection section, String key, ConfigurationSection relativeRoot) {
        Configuration root = section.getRoot();

        if (root == null) {
            throw new IllegalStateException("Cannot create path without root!");
        }

        StringBuilder builder = new StringBuilder();

        if (section != null) {
            for (ConfigurationSection parent = section; (parent != null) && (parent != relativeRoot); parent = parent.getParent()) {
                if (builder.length() > 0) {
                    builder.insert(0, SEPARATOR);
                }

                builder.insert(0, parent.getName());
            }
        }

        if ((key != null) && (!key.isEmpty())) {
            if (builder.length() > 0) {
                builder.append(SEPARATOR);
            }

            builder.append(key);
        }

        return builder.toString();
    }

    @Override
    public ConfigurationSection getSection(String path) {
        Object obj = map.get(path);
        return (obj instanceof ConfigurationSection) ? (ConfigurationSection) obj : null;
    }

    private static boolean isPrimative(Object object) {
        return (object instanceof Integer) || (object instanceof Boolean)
                || (object instanceof Double) || (object instanceof String) || (object instanceof Float);
    }

    public Configuration getRoot() {
        return this.root;
    }

    public ConfigurationSection getParent() {
        return this.parent;
    }

    @Override
    public boolean containsValue(String path) {
        return map.containsKey(createPath(this, path));
    }
}
