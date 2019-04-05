package com.altarisnine.networkcore.api.guard.spatial.vector;

import com.altarisnine.networkcore.api.configuration.ConfigurationSection;
import com.altarisnine.networkcore.api.configuration.objects.MemoryConfiguration;
import com.altarisnine.networkcore.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.networkcore.api.world.Location;
import com.altarisnine.networkcore.api.world.World;
import lombok.Getter;

import java.util.Objects;

public final class VectorI3 implements ConfigSerializable {
    public static final VectorI3 ZERO = new VectorI3(0, 0, 0);
    @Getter private final int x, y, z;

    public VectorI3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public VectorI3(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    public VectorI3(double x, double y, double z) {
        this((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    public boolean gt(VectorI3 v) {
        return ( x > v.x || y > v.y || z > v.z );
    }

    public boolean lt(VectorI3 v) {
        return ( x < v.x || y < v.y || z < v.z );
    }


    public VectorI2 toBlockVector2() {
        return VectorI2.of(this);
    }

    public Location toLocation(World world) {
        return toLocation(world, 0, 0);
    }

    public Location toLocation(World world, float yaw, float pitch) {
        return new Location(world, (double) x, (double) y, (double) z, yaw, pitch);
    }

    public VectorI3 mult(int scalar) {
        return new VectorI3(x * scalar, y * scalar, z * scalar);
    }

    public static VectorI3 minComponents(final VectorI3 A, final VectorI3 B) {
        return new VectorI3(Math.min(A.x, B.x), Math.min(A.y, B.y), Math.min(A.z, B.z));
    }

    public static VectorI3 maxComponents(final VectorI3 A, final VectorI3 B) {
        return new VectorI3(Math.max(A.x, B.x), Math.max(A.y, B.y), Math.max(A.z, B.z));
    }

    public static VectorI3 of(VectorI2 vector, int y) {
        return new VectorI3(vector.getX(), y, vector.getZ());
    }

    public static VectorI3 of(Location location) {
        return new VectorI3(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VectorI3)) return false;
        VectorI3 vectorI3 = (VectorI3) o;
        return x == vectorI3.x &&
                y == vectorI3.y &&
                z == vectorI3.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public static VectorI3 of(int x, int y, int z) {
        return new VectorI3(x, y, z);
    }

    @Override
    public String toString() {
        return "VectorI3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public ConfigurationSection serialize() {
        ConfigurationSection section = new MemoryConfiguration();

        section.set("x", x);
        section.set("y", y);
        section.set("z", z);

        return section;
    }

    public static VectorI3 deserialize(ConfigurationSection section) {
        return new VectorI3(section.getInteger("x"),
                section.getInteger("y"),
                section.getInteger("z"));
    }
}
