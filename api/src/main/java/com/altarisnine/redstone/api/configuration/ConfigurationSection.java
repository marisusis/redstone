package com.altarisnine.redstone.api.configuration;

import com.altarisnine.redstone.api.configuration.objects.MemoryConfiguration;
import com.altarisnine.redstone.api.configuration.serialization.ConfigSerializable;

import java.util.Map;
import java.util.UUID;

/**
 * The interface Configuration section.
 */
public interface ConfigurationSection {
    /**
     * Gets the root of the {@link ConfigurationSection}
     *
     * @return the root
     */
    Configuration getRoot();

    /**
     * Gets the name of the configuration section, which is used in the path.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets the parent {@link ConfigurationSection}. Returns null if this is the root node.
     *
     * @return the parent
     */
    ConfigurationSection getParent();

    boolean containsValue(String path);

    /**
     * Set.
     *
     * @param path the path
     * @param obj  the obj
     */
    <T> void set(String path, T obj);

    /**
     * Gets an object with type
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @param path  the path
     * @return the t
     */
    <T extends ConfigSerializable> T get(Class<T> clazz, String path);

    <T extends Enum> T getEnum(Class<T> clazz, String path);

    /**
     * Gets a {@link Integer} value. Returns null if path doesn't exist or not a {@link Integer}.
     *
     * @param path the path
     * @return the integer
     */
    int getInteger(String path);

    /**
     * Gets a {@link Boolean} value. Returns null if path doesn't exist or not a {@link Boolean}.
     *
     * @param path the path
     * @return the boolean
     */
    boolean getBoolean(String path);

    /**
     * Gets a {@link Double} value. Returns null if path doesn't exist or not a {@link Double}.
     *
     * @param path the path
     * @return the double
     */
    double getDouble(String path);

    float getFloat(String path);

    UUID getUUID(String path);

    /**
     * Gets a {@link String} value
     *
     * @param path the path
     * @return the string
     */
    String getString(String path);

    /**
     * Gets a {@link Map} of all values in the config.
     *
     * @param deep the deep
     * @return the values
     */
    Map<String, Object> getValues(boolean deep);

    /**
     * Create a {@link ConfigurationSection} at the specified path
     *
     * @param path the path
     * @return the configuration section
     */
    ConfigurationSection createSection(String path);

    /**
     * Gets the {@link ConfigurationSection} at the specified path. Returns null if path doesn't exist or not a {@link ConfigurationSection}.
     *
     * @param path the path
     * @return the section
     */
    ConfigurationSection getSection(String path);

    public static ConfigurationSection createBlank() {
        return new MemoryConfiguration();
    }
}
