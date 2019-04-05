package com.altarisnine.networkcore.api;

public final class Core {
    private static volatile CoreAPI api;

    public static CoreAPI getApi() throws IllegalStateException {
        if (api == null)
            throw new IllegalStateException("NetworkCore is not yet initialized");
        return api;
    }

    public static void setApi(CoreAPI instance) {
        api = instance;
    }
}
