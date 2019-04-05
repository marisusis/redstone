package com.altarisnine.networkcore.api.inventory;

import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.text.Text;

// TODO different inventory types
public interface InventoryView {

    Inventory getTopInventory();

    Inventory getBottomInventory();

    Player getPlayer();

    void setItem(int slot, Item item);

    Item getItem(int slot);

    void setItemOnCursor(Item item);

    Item getItemOnCursor();

    void close();

    int slots();

    Text getTitle();

}
