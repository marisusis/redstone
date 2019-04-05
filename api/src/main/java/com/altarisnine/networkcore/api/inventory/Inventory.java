package com.altarisnine.networkcore.api.inventory;

import com.altarisnine.networkcore.api.entity.living.player.Player;

import java.util.List;

public interface Inventory {
    int getSize();

    void setItem(int slot, Item item);

    Item getItem(int slot);

    List<Player> getViewers();

    void addItem(Item item);
}
