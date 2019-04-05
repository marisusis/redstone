package com.altarisnine.networkcore.api.storage;

public final class StorageValue {
    private String value;
    private boolean persistent;

    public StorageValue(String value, boolean persistent) {
        this.value = value;
        this.persistent = persistent;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isPersistent() {
        return this.persistent;
    }
}
