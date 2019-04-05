package com.altarisnine.networkcore.api.inventory;

import com.altarisnine.networkcore.api.entity.living.player.Player;

public interface PlayerInventory extends Inventory {

    Item[] getArmor();

    void setArmor(Item[] armor);

    Item getHelmet();

    void setHelmet(Item item);

    Item getChestplate();

    void setChestplate(Item item);

    Item getLeggings();

    void setLeggings(Item item);

    Item getBoots();

    void setBoots(Item item);

    Item getItemInMainHand();

    void setItemInMainHand(Item item);

    Item getItemInOffHand();

    void setItemInOffHand(Item item);

    Player getHolder();

}
