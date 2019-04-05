package com.altarisnine.redstone.bukkit.inventory;

import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.inventory.PlayerInventory;
import com.altarisnine.redstone.bukkit.Converter;
import com.altarisnine.redstone.common.RedstoneCore;

public class BukkitPlayerInventory extends BukkitInventory implements PlayerInventory {
    private final org.bukkit.inventory.PlayerInventory inventory;

    public BukkitPlayerInventory(RedstoneCore instance, org.bukkit.inventory.PlayerInventory inventory) {
        super(instance, inventory);
        this.inventory = inventory;
    }

    @Override
    public Item[] getArmor() {
        return Converter.items(inventory.getArmorContents());
    }

    @Override
    public void setArmor(Item[] armor) {
        inventory.setArmorContents(Converter.items(armor));
    }

    @Override
    public Item getHelmet() {
        return Converter.item(inventory.getHelmet());
    }

    @Override
    public void setHelmet(Item item) {
        inventory.setHelmet(Converter.item(item));
    }

    @Override
    public Item getChestplate() {
        return Converter.item(inventory.getChestplate());
    }

    @Override
    public void setChestplate(Item item) {
        inventory.setChestplate(Converter.item(item));
    }

    @Override
    public Item getLeggings() {
        return Converter.item(inventory.getLeggings());
    }

    @Override
    public void setLeggings(Item item) {
        inventory.setLeggings(Converter.item(item));
    }

    @Override
    public Item getBoots() {
        return Converter.item(inventory.getBoots());
    }

    @Override
    public void setBoots(Item item) {
        inventory.setBoots(Converter.item(item));
    }

    @Override
    public Item getItemInMainHand() {
        return Converter.item(inventory.getItemInMainHand());
    }

    @Override
    public void setItemInMainHand(Item item) {
        inventory.setItemInMainHand(Converter.item(item));
    }

    @Override
    public Item getItemInOffHand() {
        return Converter.item(inventory.getItemInOffHand());
    }

    @Override
    public void setItemInOffHand(Item item) {
        inventory.setItemInOffHand(Converter.item(item));
    }

    @Override
    public Player getHolder() {
        return core.getPlayerManager().getPlayer(inventory.getHolder().getUniqueId());
    }
}
