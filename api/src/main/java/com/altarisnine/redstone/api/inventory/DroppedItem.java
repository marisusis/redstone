package com.altarisnine.redstone.api.inventory;

import com.altarisnine.redstone.api.entity.Entity;

public interface DroppedItem extends Entity {
    Item getItem();
    void setItem(Item item);

    int getPickupDelay();
    void setPickupDelay(int delay);
}
