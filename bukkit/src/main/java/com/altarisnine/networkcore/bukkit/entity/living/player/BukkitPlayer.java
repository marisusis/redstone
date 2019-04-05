package com.altarisnine.networkcore.bukkit.entity.living.player;

import com.altarisnine.networkcore.api.block.Block;
import com.altarisnine.networkcore.api.block.Material;
import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.inventory.InventoryAction;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.inventory.PlayerInventory;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Vector;
import com.altarisnine.networkcore.api.world.Location;
import com.altarisnine.networkcore.bukkit.Converter;
import com.altarisnine.networkcore.bukkit.inventory.BukkitPlayerInventory;
import com.altarisnine.networkcore.bukkit.world.block.BukkitBlock;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.entity.living.player.CorePlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

import java.util.*;

public final class BukkitPlayer extends CorePlayer {

    private final org.bukkit.entity.Player player;

    public BukkitPlayer(NetworkCore instance, UUID playerUUID) {
        super(instance, playerUUID);
        this.player = Bukkit.getServer().getPlayer(playerUUID);
    }

    public void sendToServer(String serverName) {
        throw new IllegalStateException("cannot send to server from non bungee atm");
    }

    @Override
    public void closeInventory() {
        player.closeInventory();
    }

    @Override
    public PlayerInventory getInventory() {
        return new BukkitPlayerInventory(core, player.getInventory());
    }

    @Override
    public void setLevel(int level) {
        player.setLevel(level);
    }

    @Override
    public int getLevel() {
        return player.getLevel();
    }

    @Override
    public void setListName(Text name) {
        player.setPlayerListName(Converter.parse(name).toLegacyText());
    }

    @Override
    public void addInventoryAction(int slot, InventoryAction action) {

    }

    @Override
    public Item getItemInHand() {
        return Converter.item(player.getInventory().getItemInMainHand());
    }

    @Override
    public void sendActionBar(Text text) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text.toFormat()));
    }

    @Override
    public void sendFakeBlock(Material material, Location location) {
        player.sendBlockChange(Converter.location(location), player.getServer().createBlockData(Converter.blockType(material)));
    }

    @Override
    public boolean isSneaking() {
        return player.isSneaking();
    }

    @Override
    public void setSneaking(boolean sneaking) {
        player.setSneaking(sneaking);
    }

    @Override
    public boolean isFlying() {
        return player.isFlying();
    }

    @Override
    public void setFlying(boolean flying) {
        player.setFlying(flying);
    }

    @Override
    public boolean isSprinting() {
        return player.isSprinting();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        player.setSprinting(sprinting);
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        player.playSound(Converter.location(location), sound, volume, pitch);
    }

    @Override
    public void stopSound(String sound) {
        player.stopSound(sound);
    }

    @Override
    public void setCompassLocation(Location location) {
        player.setCompassTarget(Converter.location(location));
    }

    @Override
    public void kick(String reason) {
        player.kickPlayer(reason);
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void sendMessage(Text text) {
        player.spigot().sendMessage(Converter.parse(text));
    }

    @Override
    public void damage(double damage) {
        player.damage(damage);
    }

    @Override
    public void damage(double amount, Entity source) {

    }

    @Override
    public void setHealth(double health) {
        player.setHealth(health);
    }

    @Override
    public double getHealth() {
        return player.getHealth();
    }

    @Override
    public void kill() {
        player.setHealth((double) 0);
    }

    @Override
    public Location getLocation() {
        return Converter.location(player.getLocation());
    }

    @Override
    public void teleport(Location location) {
        player.teleport(Converter.location(location));
    }

    @Override
    public void setVelocity(Vector velocity) {
        player.setVelocity(Converter.vector(velocity));
    }

    @Override
    public Vector getVelocity() {
        return Converter.vector(player.getVelocity());
    }

    @Override
    public boolean isOnGround() {
        return player.isOnGround();
    }

    @Override
    public void remove() {
        player.remove();
    }

    @Override
    public boolean isDead() {
        return player.isDead();
    }

    @Override
    public boolean isValid() {
        return player.isValid();
    }

    @Override
    public float getFallDistance() {
        return player.getFallDistance();
    }

    @Override
    public void setFallDistance(float distance) {
        player.setFallDistance(distance);
    }

    @Override
    public boolean isInsideVehicle() {
        return player.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return player.leaveVehicle();
    }

    @Override
    public Entity getVehicle() {
        return Converter.getEntity(player.getVehicle());
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        player.setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return player.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean flag) {
        player.setGlowing(flag);
    }

    @Override
    public boolean isGlowing() {
        return player.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        player.setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return player.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return player.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        player.setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return player.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        player.setGravity(gravity);
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int distance) {
        Set<org.bukkit.Material> transparents = EnumSet.noneOf(org.bukkit.Material.class);

        for (Material material : transparent) {
            transparents.add(Converter.blockType(material));
        }

        List<Block> result = new ArrayList<>();

        for (org.bukkit.block.Block block : player.getLineOfSight(transparents, distance)) {
            result.add(new BukkitBlock(block));
        }

        return result;
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int distance) {
        if (transparent == null) return new BukkitBlock(player.getTargetBlock(null, distance));
        Set<org.bukkit.Material> transparents = EnumSet.noneOf(org.bukkit.Material.class);

        for (Material material : transparent) {
            transparents.add(Converter.blockType(material));
        }

        return new BukkitBlock(player.getTargetBlock(transparents, distance));
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        throw new UnsupportedOperationException("not implemented!");
    }

    @Override
    public boolean isGliding() {
        return player.isGliding();
    }

    @Override
    public void setGliding(boolean gliding) {
        player.setGliding(gliding);
    }

    @Override
    public void setCollidable(boolean collidable) {
        player.setCollidable(collidable);
    }

    @Override
    public boolean isCollidable() {
        return player.isCollidable();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }
}
