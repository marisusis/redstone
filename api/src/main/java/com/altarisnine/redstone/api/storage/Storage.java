package com.altarisnine.redstone.api.storage;

/**
 * The interface Storage.
 */
public interface Storage {


    /**
     * Init.
     *
     * @param clazz the clazz
     */
    void init(Class<?> clazz);

    /**
     * Initialize a group of classes.
     *
     * @param classes the classes
     */
    void init(Class<?>... classes);


    /**
     * Load all existing objects with the specified type from the database.
     *
     * @param clazz the clazz
     */
    void loadAll(Class<?> clazz);
}
