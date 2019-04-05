package com.altarisnine.networkcore.common.logging;

import com.altarisnine.networkcore.api.util.Logger;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.util.ServerType;

// MUST fix logger implementation
public final class CoreLogger implements Logger {
    protected NetworkCore plugin;

    public CoreLogger(NetworkCore instance) {
        plugin = instance;
    }

    public void info(String message) {
        log(message);
    }

    public void error(String message) {
        log("&c[ERROR] " + message);
    }

    public void success(String message) {
        log("&a[OK] " + message);
    }
    
    public void debug(String message) {
        log("&8[DEBUG] " + message);
    }

    public String getPrefix() {
        if (plugin.getType().equals(ServerType.PROXY)) {
            return "&5[&dNetworkCore&5] ";
        } else if (plugin.getType().equals(ServerType.INSTANCE)) {
            return "&2[&aNetworkCore&2] ";
        } else if (plugin.getType().equals(ServerType.GLASS)) {
            return "";
        } else {
            return "&6[&eNetworkCore&6] ";
        }
    }

    private void log(String message) {
        plugin.log(getPrefix() + message);
    }
}