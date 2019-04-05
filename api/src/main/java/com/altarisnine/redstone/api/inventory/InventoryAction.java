package com.altarisnine.redstone.api.inventory;

@FunctionalInterface
public interface InventoryAction {
    void action(Inventory inventory, Item item, int slot);
}
