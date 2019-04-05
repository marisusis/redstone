package com.altarisnine.networkcore.api.storage.sync;

/**
 * The interface Syncable.
 */
public interface Syncable {
    /**
     * Sets the value of a field.
     *
     * @param key   the key
     * @param value the value
     */
    void setField(String key, String value);

    /**
     * Gets the value of a field.
     *
     * @param key the key
     * @return the field
     */
    String getField(String key);
}
