package com.altarisnine.redstone.api.gui;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.inventory.Item;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * A base class for all GUIs
 * Inventory GUIs are used to create menus, in the event more interaction than just a menu is needed, a separate helper should be created.
 */
public abstract class GUI {
    @Getter private final int rows;
    @Getter private final Map<Integer, GUIAction> actions;
    @Getter private final Map<Integer, Item> items;

    /**
     * Instantiates a new GUI.
     *
     * @param rows the rows
     */
    public GUI(int rows) {
        this.rows = rows;
        this.actions = new HashMap<>(rows * 9);
        this.items = new HashMap<>(rows * 9);
    }

    /**
     * Gets the number of slots in the GUI
     *
     * @return the size
     */
    public final int getSize() {
        return rows * 9;
    }

    /**
     * Assigns a {@link GUIAction} to a specific slot
     *
     * @param slot   the slot
     * @param action the action
     */
    public void setAction(int slot, GUIAction action) {
        actions.put(slot, action);
    }

    /**
     * Assigns an {@link Item} to a specific slot
     *
     * @param slot the slot
     * @param item the item
     */
    public void setItem(int slot, Item item) {
        items.put(slot, item);
    }

    /**
     * Fire the {@link GUIAction} in the specified slot
     *
     * @param player the player
     * @param slot   the slot
     */
    public final void fireAction(Player player, int slot) {
        // Check if there is an item in the slot
        Item item = items.get(slot);

        actions.get(slot).action(player, item);
    }

    /**
     * Check if the specified raw slot is in the GUI
     *
     * @param rawSlot the raw slot
     * @return the boolean
     */
    public final boolean inGUI(int rawSlot) {
        return rawSlot < getSize();
    }

    public final boolean isFilledSlot(int slot) {
        return items.containsKey(slot);
    }

    public final boolean isActiveSlot(int slot) {
        return actions.containsKey(slot);
    }

}
