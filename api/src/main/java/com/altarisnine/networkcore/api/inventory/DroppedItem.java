package com.altarisnine.networkcore.api.inventory;

import com.altarisnine.networkcore.api.entity.Entity;

public interface DroppedItem extends Entity {
    Item getItem();
    void setItem(Item item);

    int getPickupDelay();
    void setPickupDelay(int delay);
}
