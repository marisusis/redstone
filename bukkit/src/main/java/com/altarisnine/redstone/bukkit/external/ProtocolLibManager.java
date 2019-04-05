package com.altarisnine.redstone.bukkit.external;

import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.external.ExternalManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class ProtocolLibManager implements ExternalManager {
    private RedstoneCore plugin;

    private final ProtocolManager protocolManager;

    public ProtocolLibManager(RedstoneCore instance) {
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
