package com.altarisnine.redstone.api.guard.flag;

import java.util.HashMap;
import java.util.Map;

public final class Flags {
    public static final Map<String, Flag<?>> flags = new HashMap<>();

    public static final PermissibleFlag ENTRY = register(new PermissibleFlag("entry"));
    public static final PermissibleFlag EXIT = register(new PermissibleFlag("exit"));
    public static final PermissibleFlag STORAGE = register(new PermissibleFlag("storage"));
    public static final PermissibleFlag BUILD = register(new PermissibleFlag("build", PermissibleFlag.Perm.ADMIN));

    private static <T, R extends Flag<T>> R register(Flag<T> flag) {
        flags.put(flag.getName().toUpperCase(), flag);
        return (R) flag;
    }
}