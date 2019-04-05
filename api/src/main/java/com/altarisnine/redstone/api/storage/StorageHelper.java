package com.altarisnine.redstone.api.storage;

public abstract class StorageHelper {

    public abstract StorageGroup initForSync(StorageGroup group);

    public abstract StorageGroup initForDatabase(StorageGroup group);

    public abstract StorageGroup getDefault();

    public static StorageHelper getHelper(Class<?> clazz) {
        if (clazz.isAnnotationPresent(StoredBy.class)) {
            StoredBy anno = clazz.getAnnotation(StoredBy.class);
            try {
                return anno.helper().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        throw new RuntimeException("replace this please");
    }

}
