package com.altarisnine.redstone.api.plugin;

public class InvalidPluginException extends Exception {
    public InvalidPluginException(Throwable cause) {
        super(cause);
    }

    public InvalidPluginException(String message, Exception e) {
        super(message, e);
    }
}
