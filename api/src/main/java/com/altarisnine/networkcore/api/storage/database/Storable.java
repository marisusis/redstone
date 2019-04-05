package com.altarisnine.networkcore.api.storage.database;

import com.altarisnine.networkcore.api.storage.StorageGroup;

public interface Storable<S> {
    StorageGroup toStorageGroup();
}
