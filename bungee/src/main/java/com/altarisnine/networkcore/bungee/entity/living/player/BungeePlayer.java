package com.altarisnine.networkcore.bungee.entity.living.player;

import com.altarisnine.networkcore.api.block.Block;
import com.altarisnine.networkcore.api.block.Material;
import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.inventory.InventoryAction;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.inventory.PlayerInventory;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Vector;
import com.altarisnine.networkcore.api.world.Location;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.entity.living.player.CorePlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BungeePlayer extends CorePlayer {

    private final ProxiedPlayer player;

    public BungeePlayer(NetworkCore instance, ProxiedPlayer player) {
        super(instance, player.getUniqueId());
        this.player = player;
    }


    public void sendToServer(String serverName) {
        ServerInfo info = ProxyServer.getInstance().getServerInfo(serverName);
        player.connect(info);
    }

    @Override
    public void closeInventory() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public PlayerInventory getInventory() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setLevel(int level) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public int getLevel() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setListName(Text name) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void addInventoryAction(int slot, InventoryAction action) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public Item getItemInHand() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void sendActionBar(Text text) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void sendFakeBlock(Material material, Location location) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public void setMuted(boolean muted) {
        super.setMuted(muted);
    }

    @Override
    public boolean isMuted() {
        return super.isMuted();
    }

    @Override
    public boolean isSneaking() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setSneaking(boolean sneaking) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isFlying() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setFlying(boolean flying) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isSprinting() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setSprinting(boolean sprinting) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void stopSound(String sound) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setCompassLocation(Location location) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void kick(String reason) {
        player.disconnect(TextComponent.fromLegacyText(reason));
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(TextComponent.fromLegacyText(message));
    }

    @Override
    public void sendMessage(Text text) {
        sendMessage(ChatColor.translateAlternateColorCodes('&', text.getContent()));
    }

    @Override
    public void damage(double damage) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void damage(double amount, Entity source) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setHealth(double health) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public double getHealth() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void kill() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public Location getLocation() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void teleport(Location location) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setVelocity(Vector velocity) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public Vector getVelocity() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isOnGround() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public float getFallDistance() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setFallDistance(float distance) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isInsideVehicle() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean leaveVehicle() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public Entity getVehicle() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isCustomNameVisible() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setGlowing(boolean flag) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isGlowing() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setInvulnerable(boolean flag) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isInvulnerable() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isSilent() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setSilent(boolean flag) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean hasGravity() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setGravity(boolean gravity) {

    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int distance) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int distance) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isGliding() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setGliding(boolean gliding) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public void setCollidable(boolean collidable) {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }

    @Override
    public boolean isCollidable() {
        throw new UnsupportedOperationException("BungeeCord not allowed");
    }
}
