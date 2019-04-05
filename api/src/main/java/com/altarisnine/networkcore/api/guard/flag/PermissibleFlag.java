package com.altarisnine.networkcore.api.guard.flag;

public class PermissibleFlag extends Flag<PermissibleFlag.Perm> {

    private final Perm defaultValue;

    public PermissibleFlag(String name) {
        this(name, Perm.ALLOW);
    }

    public PermissibleFlag(String name, Perm defaultValue) {
        super(name);
        this.defaultValue = defaultValue;
    }

    @Override
    public Perm getDefault() {
        return defaultValue;
    }

    @Override
    public Perm parseValue(String input) {
        return Perm.valueOf(input.toUpperCase());
    }

    public enum Perm {
        ALLOW,
        ADMIN,
        SRMOD,
        MOD,
        TRAINEE,
        BUILDER,
        DENY
    }

}
