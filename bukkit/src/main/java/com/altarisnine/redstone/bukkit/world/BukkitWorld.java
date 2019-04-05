package com.altarisnine.redstone.bukkit.world;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.block.Block;
import com.altarisnine.redstone.api.entity.Entity;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.inventory.DroppedItem;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.util.Time;
import com.altarisnine.redstone.api.world.Location;
import com.altarisnine.redstone.api.world.Particle;
import com.altarisnine.redstone.api.world.World;
import com.altarisnine.redstone.bukkit.Converter;
import com.altarisnine.redstone.bukkit.world.block.BukkitBlock;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BukkitWorld implements World {

    private org.bukkit.World world;

    public BukkitWorld(org.bukkit.World world) {
        this.world = world;
    }

    @Override
    public String getName() {
        return world.getName();
    }

    @Override
    public Block getBlockAt(int x, int y, int z) {
        return new BukkitBlock(world.getBlockAt(x, y, z));
    }

    @Override
    public Block getBlockAt(Location location) {
        return getBlockAt((int) location.getX(), (int) location.getY(), (int) location.getZ());
    }

    @Override
    public void setTime(long time) {
        world.setTime(time);
    }

    @Override
    public long getTimeLong() {
        return world.getTime();
    }

    @Override
    public Time getTime() {
        return Time.of(world.getTime());
    }

    @Override
    public void setTime(Time time) {
        world.setTime(time.getTicks());
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z) {
        world.spawnParticle(org.bukkit.Particle.valueOf(particle.toString()), x, y, z, 0, Float.MIN_VALUE, 1, 1);
    }

    @Override
    public DroppedItem dropItem(Location location, Item item) {
        return (DroppedItem) Converter.entity(world.dropItem(Converter.location(location), Converter.item(item)));
    }

    @Override
    public DroppedItem dropItemNaturally(Location location, Item item) {
        return (DroppedItem) Converter.entity(world.dropItemNaturally(Converter.location(location), Converter.item(item)));
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> cls) {
        return world.getEntitiesByClass(Converter.getEntityClass(cls)).stream().map(e -> (T) Converter.entity(e)).collect(Collectors.toList());
    }

    @Override
    public Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<Player> getPlayers() {
        return world.getPlayers().stream().map(p -> Redstone.getApi().getPlayer(p.getUniqueId())).collect(Collectors.toList());
    }

    @Override
    public boolean hasStorm() {
        return world.hasStorm();
    }

    @Override
    public void setStorm(boolean hasStorm) {
        world.setStorm(hasStorm);
    }

    @Override
    public int getWeatherDuration() {
        return world.getWeatherDuration();
    }

    @Override
    public void setWeatherDuration(int duration) {
        world.setWeatherDuration(duration);
    }

    @Override
    public boolean isThundering() {
        return world.isThundering();
    }

    @Override
    public void setThundering(boolean thundering) {
        world.setThundering(thundering);
    }

    @Override
    public int getThunderDuration() {
        return world.getThunderDuration();
    }

    @Override
    public void setThunderDuration(int duration) {
        world.setThunderDuration(duration);
    }

    @Override
    public int getHighestBlockYAt(int x, int z) {
        return world.getHighestBlockYAt(x, z);
    }

    @Override
    public int getHighestBlockYAt(Location location) {
        return world.getHighestBlockYAt(Converter.location(location));
    }

    @Override
    public Block getHighestBlockAt(int x, int z) {
        return new BukkitBlock(world.getHighestBlockAt(x, z));
    }

    @Override
    public Block getHighestBlockAt(Location location) {
        return new BukkitBlock(world.getHighestBlockAt(Converter.location(location)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BukkitWorld that = (BukkitWorld) o;
        return Objects.equals(world.getName(), that.world.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(world);
    }
}