package com.altarisnine.redstone.api.world;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.configuration.ConfigurationSection;
import com.altarisnine.redstone.api.configuration.objects.MemoryConfiguration;
import com.altarisnine.redstone.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.redstone.api.guard.spatial.vector.VectorI3;
import com.altarisnine.redstone.api.util.Vector;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public final class Location implements ConfigSerializable {

    @Getter @Setter private World world;
    @Getter @Setter private double x, y, z;
    @Getter @Setter private float pitch, yaw;

    public Location(World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public Location(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Vector toVector() {
        return new Vector(x, y, z);
    }

    public VectorI3 toBlockVector() {
        return new VectorI3(getBlockX(), getBlockY(), getBlockZ());
    }

    public void transform(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
    }

    public int getBlockX() {
        return (int) Math.round(x);
    }

    public int getBlockY() {
        return (int) Math.round(y);
    }

    public int getBlockZ() {
        return (int) Math.round(z);
    }

    public static boolean differentBlock(Location A, Location B) {
        return A.x != B.x || A.y != B.y || A.z != B.z;
    }

    public ConfigurationSection serialize() {
        ConfigurationSection section = new MemoryConfiguration();

        section.set("x", x);
        section.set("y", y);
        section.set("z", z);
        section.set("world", world.getName());
        section.set("pitch", pitch);
        section.set("yaw", yaw);

        return section;
    }

    public static Location deserialize(ConfigurationSection section) {
        final Location location = new Location(Redstone.getApi().getWorld(section.getString("world")),
                section.getDouble("x"),
                section.getDouble("y"),
                section.getDouble("z"));

        if (section.containsValue("yaw") && section.containsValue("pitch")) {
            location.setPitch(section.getFloat("pitch"));
            location.setYaw(section.getFloat("yaw"));
        }
        return location;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Double.compare(location.x, x) == 0 &&
                Double.compare(location.y, y) == 0 &&
                Double.compare(location.z, z) == 0 &&
                Float.compare(location.pitch, pitch) == 0 &&
                Float.compare(location.yaw, yaw) == 0 &&
                world.equals(location.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, x, y, z, pitch, yaw);
    }
}
