package com.altarisnine.networkcore.bukkit.inventory;

import com.altarisnine.networkcore.api.inventory.DroppedItem;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.bukkit.Converter;
import com.altarisnine.networkcore.bukkit.entity.BukkitEntity;

public class BukkitDroppedItem extends BukkitEntity implements DroppedItem {

    private final org.bukkit.entity.Item entity;

    public BukkitDroppedItem(org.bukkit.entity.Item item) {
        super(item);
        this.entity = item;
    }

    @Override
    public Item getItem() {
        return Converter.item(entity.getItemStack());
    }

    @Override
    public void setItem(Item item) {
        this.entity.setItemStack(Converter.item(item));
    }

    @Override
    public int getPickupDelay() {
        return entity.getPickupDelay();
    }

    @Override
    public void setPickupDelay(int delay) {
        entity.setPickupDelay(delay);
    }
}
