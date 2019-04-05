package com.altarisnine.networkcore.common.storage;

import lombok.Getter;

public class StorageField {
    @Getter private String key;
    @Getter private Persistence persistence;
    @Getter private String defaultKeySource;

    public StorageField(String key, Persistence persistence) {
        this(key, persistence, null);
    }

    public StorageField(String key, Persistence persistence, String defaultKeySource) {
        this.key = key;
        this.persistence = persistence;
        this.defaultKeySource = defaultKeySource;
    }
}
