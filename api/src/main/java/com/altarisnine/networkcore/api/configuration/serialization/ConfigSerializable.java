package com.altarisnine.networkcore.api.configuration.serialization;


import com.altarisnine.networkcore.api.configuration.ConfigurationSection;

/**
 * The interface Configuration serializable.
 *
 * You must also have a static method deserialize
 */
public interface ConfigSerializable {
    /**
     * Serialize configuration section.
     *
     * @return the configuration section
     */
    ConfigurationSection serialize();
}
