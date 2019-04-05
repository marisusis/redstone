package com.altarisnine.networkcore.api.storage.sync;

import com.altarisnine.networkcore.api.storage.StorageGroup;

public interface Sync {

    void writeGroup(StorageGroup group);

    StorageGroup readGroup(String namespace, String id);

    void writeGroupValue(String namespace, String id, String field, String value);

    String readGroupValue(String namespace, String id, String field);

    void writeValue(String field, String value);

    String readValue(String field);
}
