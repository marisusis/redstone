package com.altarisnine.redstone.api;

public final class Redstone {
    private static volatile RedstoneAPI api;

    public static RedstoneAPI getApi() throws IllegalStateException {
        if (api == null)
            throw new IllegalStateException("Redstone is not yet initialized");
        return api;
    }

    public static void setApi(RedstoneAPI instance) {
        api = instance;
    }
}
