package com.altarisnine.redstone.common.user;

import java.util.UUID;

// TODO replace with sender interface, implemented by both console and player, move from Common to API
public interface User {
    void sendMessage(String message);
    boolean isPlayer();
    UUID getUniqueId();
    String getName();
    boolean hasPermission(String permission);
}