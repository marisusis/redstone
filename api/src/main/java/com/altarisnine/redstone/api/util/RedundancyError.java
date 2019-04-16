package com.altarisnine.redstone.api.util;

public class RedundancyError extends Error {
    public RedundancyError(Class<?> clazz) {
        super("Initializing class " + clazz.getName() + " here is redundant and will break things!");
    }
}
