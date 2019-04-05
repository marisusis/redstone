package com.altarisnine.redstone.api.gui;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.inventory.Item;

@FunctionalInterface
public interface GUIAction {
    void action(Player clicker, Item clicked);
}
