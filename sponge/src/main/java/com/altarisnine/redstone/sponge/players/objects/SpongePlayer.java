package com.altarisnine.redstone.sponge.players.objects;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.block.Material;
import com.altarisnine.redstone.api.entity.Entity;
import com.altarisnine.redstone.api.inventory.InventoryAction;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.inventory.PlayerInventory;
import com.altarisnine.redstone.api.util.Vector;
import com.altarisnine.redstone.api.world.Location;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.entity.living.player.CorePlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SpongePlayer extends CorePlayer {

    private org.spongepowered.api.entity.living.player.Player player;

    public SpongePlayer(RedstoneCore core, UUID playerUUID) {
        super(core, playerUUID);
        this.player = Sponge.getServer().getPlayer(playerUUID).get();
    }

    @Override
    public void setMuted(boolean muted) {

    }

    @Override
    public boolean isMuted() {
        return false;
    }

    @Override
    public boolean isSneaking() {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public void setSneaking(boolean sneaking) {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public boolean isFlying() {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public void setFlying(boolean flying) {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public boolean isSprinting() {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public void setSprinting(boolean sprinting) {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public void stopSound(String sound) {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public void setCompassLocation(Location location) {
        throw new UnsupportedOperationException("Sponge not allowed");
    }

    @Override
    public void kick(String reason) {
        player.kick(Text.of(reason));
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(Text.of(message));
    }

    @Override
    public void sendMessage(com.altarisnine.redstone.api.text.Text text) {
        sendMessage(text.getContent());
    }

    @Override
    public void sendToServer(String serverName) {
        throw new IllegalStateException("Sponge not allowed!");
    }

    @Override
    public void closeInventory() {
        throw new IllegalStateException("Sponge not allowed!");
    }

    @Override
    public PlayerInventory getInventory() {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public void setLevel(int level) {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public int getLevel() {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public void setListName(com.altarisnine.redstone.api.text.Text name) {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public void addInventoryAction(int slot, InventoryAction action) {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public Item getItemInHand() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void sendActionBar(com.altarisnine.redstone.api.text.Text text) {
        throw new UnsupportedOperationException("BungeeCord not allowed");

    }

    @Override
    public void sendFakeBlock(Material material, Location location) {

    }

    @Override
    public void damage(double damage) {
        throw new UnsupportedOperationException("Sponge not implemented");
    }

    @Override
    public void damage(double amount, Entity source) {

    }

    @Override
    public void setHealth(double health) {
        player.health().set(health);
    }

    @Override
    public double getHealth() {
        return player.health().get();
    }

    @Override
    public void kill() {
        player.health().set(0.0d);
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public void teleport(Location location) {
        Redstone.getApi().getLogger().info("teleport");
    }

    @Override
    public void setVelocity(Vector velocity) {

    }

    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public boolean isOnGround() {
        return false;
    }

    @Override
    public void remove() {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public float getFallDistance() {
        return 0;
    }

    @Override
    public void setFallDistance(float distance) {

    }

    @Override
    public boolean isInsideVehicle() {
        return false;
    }

    @Override
    public boolean leaveVehicle() {
        return false;
    }

    @Override
    public Entity getVehicle() {
        return null;
    }

    @Override
    public void setCustomNameVisible(boolean flag) {

    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public void setGlowing(boolean flag) {

    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public void setInvulnerable(boolean flag) {

    }

    @Override
    public boolean isInvulnerable() {
        return false;
    }

    @Override
    public boolean isSilent() {
        return false;
    }

    @Override
    public void setSilent(boolean flag) {

    }

    @Override
    public boolean hasGravity() {
        return false;
    }

    @Override
    public void setGravity(boolean gravity) {

    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int distance) {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int distance) {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public boolean isGliding() {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public void setGliding(boolean gliding) {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public void setCollidable(boolean collidable) {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }

    @Override
    public boolean isCollidable() {
        throw new UnsupportedOperationException("Sponge not implemented!");
    }
}
