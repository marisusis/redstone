package com.altarisnine.redstone.api.entity.living.player;

import com.altarisnine.redstone.api.block.Material;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.entity.LivingEntity;
import com.altarisnine.redstone.api.entity.living.AnimalTamer;
import com.altarisnine.redstone.api.guard.spatial.boundary.Boundary;
import com.altarisnine.redstone.api.guard.spatial.selection.Selector;
import com.altarisnine.redstone.api.inventory.InventoryAction;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.inventory.PlayerInventory;
import com.altarisnine.redstone.api.storage.StoredBy;
import com.altarisnine.redstone.api.storage.helpers.PlayerStorageHelper;
import com.altarisnine.redstone.api.storage.sync.Syncable;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.world.Location;

// TODO create offline player to allow methods to ban/unban any player
@StoredBy(helper = PlayerStorageHelper.class, typeIdentifier = "players")
public interface Player extends OfflinePlayer, LivingEntity, AnimalTamer, Syncable, CommandSender {

    boolean isSneaking();
    void setSneaking(boolean sneaking);

    boolean isFlying();
    void setFlying(boolean flying);

    boolean isSprinting();
    void setSprinting(boolean sprinting);

    void playSound(Location location, String sound, float volume, float pitch);
    void stopSound(String sound);

    void setCompassLocation(Location location);

    void kick(String reason);

    Selector getSelector();
    void changeSelectorType(Class<? extends Selector> selectorType);
    void select(Boundary boundary);

    void sendToServer(String serverName);

    void closeInventory();

    PlayerInventory getInventory();

    Location getLocation();

    @Deprecated
    void setLevel(int level);

    @Deprecated
    int getLevel();

    void setListName(Text name);

    @Deprecated
        // deprecated in favor of GUI system
    void addInventoryAction(int slot, InventoryAction action);

    String getDisplayName();

    Item getItemInHand();

    void sendActionBar(Text text);

    void sendFakeBlock(Material material, Location location);
}
