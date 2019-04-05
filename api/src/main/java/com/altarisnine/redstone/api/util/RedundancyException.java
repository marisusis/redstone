package com.altarisnine.redstone.api.util;

public class RedundancyException extends RuntimeException {
    public RedundancyException(Class<?> clazz) {
        super("Initializing class " + clazz.getName() + " here is redundant and will break things!");
    }
}
