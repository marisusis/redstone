package com.altarisnine.redstone.api.inventory;

import lombok.Getter;

public enum InventoryType {
    CHEST(27, "a chest"),
    PLAYER(41, "player sh*t goes over here");

    @Getter private final int size;
    @Getter private final String defaultTitle;

    InventoryType(int size, String defaultTitle) {
        this.size = size;
        this.defaultTitle = defaultTitle;
    }

}
