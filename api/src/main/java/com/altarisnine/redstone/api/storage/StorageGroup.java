package com.altarisnine.redstone.api.storage;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class StorageGroup {

    private Map<String, StorageValue> data;

    @Deprecated @Getter @Setter
    private String key;

    @Getter @Setter
    private String namespace;

    @Getter @Setter
    private String id;

    @Deprecated
    public StorageGroup(String key) {
        this(key, new HashMap<>());
    }

    @Deprecated
    private StorageGroup(String key, Map<String, StorageValue> data) {
        this.data = data;
        this.key = key;
    }

    public StorageGroup(String namespace, String id) {
        this(namespace, id, new HashMap<>());
    }

    public StorageGroup(String namespace, String id, Map<String, StorageValue> data) {
        this.namespace = namespace;
        this.id = id;
        this.data = data;
    }

    public void set(String field, String value, boolean persistant) {
        data.put(field, new StorageValue(value, persistant));
    }

    public StorageValue get(String field) {
        return data.get(field);
    }

    public StorageGroup onlyPersistant() {
        Map<String, StorageValue> group = new HashMap<>();

        data.forEach((key, value) -> {
            if (value.isPersistent()) group.put(key, value);
        });

        return new StorageGroup(this.namespace, this.id, group);
    }

    public String getAddress() {
        return addressFor(namespace, id);
    }

    public static String addressFor(String namespace, String id) {
        return id + "@" + namespace;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StorageGroup[").append(getAddress()).append("]: ");

        data.forEach((k, v) -> builder.append(String.format("[%s]: %s", k, v.getValue())));
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (getClass() != obj.getClass())) return false;
        StorageGroup that = (StorageGroup) obj;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    public Map<String, StorageValue> getData() {
        return this.data;
    }
}
