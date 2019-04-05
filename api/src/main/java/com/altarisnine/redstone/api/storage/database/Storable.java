package com.altarisnine.redstone.api.storage.database;

import com.altarisnine.redstone.api.storage.StorageGroup;

public interface Storable<S> {
    StorageGroup toStorageGroup();
}
