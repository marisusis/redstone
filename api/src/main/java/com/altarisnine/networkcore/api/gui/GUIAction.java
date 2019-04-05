package com.altarisnine.networkcore.api.gui;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.inventory.Item;

@FunctionalInterface
public interface GUIAction {
    void action(Player clicker, Item clicked);
}
