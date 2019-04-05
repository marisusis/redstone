package com.altarisnine.networkcore.api.world;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.block.Block;
import com.altarisnine.networkcore.api.configuration.ConfigurationSection;
import com.altarisnine.networkcore.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.inventory.DroppedItem;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.util.Time;

import java.util.Collection;
import java.util.List;

public interface World extends ConfigSerializable {

    String getName();

    Block getBlockAt(int x, int y, int z);

    Block getBlockAt(Location location);

    @Deprecated
    void setTime(long time);

    @Deprecated
    long getTimeLong();

    Time getTime();

    void setTime(Time time);

    void spawnParticle(Particle particle, double x, double y, double z);

    static World deserialize(ConfigurationSection section) {
        return Core.getApi().getWorld(section.getString("name"));
    }

    DroppedItem dropItem(Location location, Item item);
    DroppedItem dropItemNaturally(Location location, Item item);
    <T extends Entity> Collection<T> getEntitiesByClass(Class<T> cls);
    Collection<Entity> getEntitiesByClasses(Class<?>... classes);
    List<Player> getPlayers();
    boolean hasStorm();
    void setStorm(boolean hasStorm);
    int getWeatherDuration();
    void setWeatherDuration(int duration);
    boolean isThundering();
    void setThundering(boolean thundering);
    int getThunderDuration();
    void setThunderDuration(int duration);
    public int getHighestBlockYAt(int x, int z);
    public int getHighestBlockYAt(Location location);
    public Block getHighestBlockAt(int x, int z);
    public Block getHighestBlockAt(Location location);

    @Override
    default ConfigurationSection serialize() {
        ConfigurationSection section = ConfigurationSection.createBlank();
        section.set("name", getName());
        return section;
    }


}
