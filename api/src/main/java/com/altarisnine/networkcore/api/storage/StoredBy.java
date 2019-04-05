package com.altarisnine.networkcore.api.storage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to define a {@link StorageHelper} for a specific class to help store in database and sync
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StoredBy {
    /**
     * The {@link StorageHelper} that is associated with this class
     *
     * @return the class
     */
    Class<? extends StorageHelper> helper();

    /**
     * String used to identify this type in the database
     *
     * @return the string
     */
    String typeIdentifier();
}
