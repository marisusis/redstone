package com.altarisnine.redstone.api.guard.flag;

import lombok.Getter;

import java.util.Objects;

// TODO permission based overrides
// TODO group based overrides
public abstract class Flag<T> {
    @Getter
    private final String name;

    public Flag(String name) {
        this.name = name;
    }

    public abstract T getDefault();
    public abstract T parseValue(String input);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flag<?> flag = (Flag<?>) o;
        return Objects.equals(name, flag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
