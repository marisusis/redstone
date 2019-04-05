package com.altarisnine.networkcore.api.inventory;

@FunctionalInterface
public interface InventoryAction {
    void action(Inventory inventory, Item item, int slot);
}
