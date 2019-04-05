package com.altarisnine.networkcore.api.block;

import lombok.Getter;

import java.util.Objects;

public final class Material {

    @Getter private final String namespace, key;

    @Deprecated
    @Getter private final int id;

    public Material(String namespace, String key) {
        this(namespace, key, -1);
    }

    @Deprecated
    public Material(String namespace, String key, int id) {
        this.namespace = namespace;
        this.key = key;
        this.id = id;
    }

    public String getFullName() {
        return (namespace + ':' + key).toLowerCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Material) {
            Material mat = (Material) obj;
            return this.getFullName().equals(mat.getFullName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getFullName());
    }

    @Override
    public String toString() {
        return "Material{" +
                "namespace='" + namespace + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
