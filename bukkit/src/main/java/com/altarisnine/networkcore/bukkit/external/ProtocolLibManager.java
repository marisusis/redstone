package com.altarisnine.networkcore.bukkit.external;

import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.external.ExternalManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class ProtocolLibManager implements ExternalManager {
    private NetworkCore plugin;

    private final ProtocolManager protocolManager;

    public ProtocolLibManager(NetworkCore instance) {
        this.plugin = instance;
        // Init ProtocoolManager
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @Override
    public void init() {

    }

    @Override
    public String getName() {
        return "ProtocolLib";
    }
}
