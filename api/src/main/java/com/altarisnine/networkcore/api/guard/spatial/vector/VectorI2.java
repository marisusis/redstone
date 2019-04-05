package com.altarisnine.networkcore.api.guard.spatial.vector;

import com.altarisnine.networkcore.api.configuration.ConfigurationSection;
import com.altarisnine.networkcore.api.configuration.objects.MemoryConfiguration;
import com.altarisnine.networkcore.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.networkcore.api.world.Location;
import com.altarisnine.networkcore.api.world.World;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public final class VectorI2 implements ConfigSerializable {
    @Getter @Setter private int x, z;

    public static final VectorI2 ZERO = new VectorI2(0, 0);

    public VectorI2(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public VectorI2(double x, double z) {
        this((int) Math.floor(x), (int) Math.floor(z));
    }


    public VectorI3 toBlockVector3() {
        return VectorI3.of(this, 0);
    }

    public VectorI3 toBlockVector3(int y) {
        return VectorI3.of(this, y);
    }

    public boolean gt(VectorI2 v) {
        return ( x > v.x || z > v.z );
    }

    public boolean lt(VectorI2 v) {
        return ( x < v.x || z < v.z );
    }

    public VectorI2 diff(VectorI2 v) {
        return new VectorI2(x - v.x, z - v.z);
    }

    public VectorI2 sum(VectorI2 v) {
        return new VectorI2(x + v.x, z + v.z);
    }

    public double mag() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
    }

    public VectorI2 mult(int scalar) {
        return new VectorI2(x * scalar, z * scalar);
    }

    public Location toLocation(World world, double y) {
        return toLocation(world, y, 0, 0);
    }

    public Location toLocation(World world, double y, float yaw, float pitch) {
        return new Location(world, (double) x, y, (double) z, yaw, pitch);
    }

    public boolean isAdjacent(VectorI2 vector) {
        VectorI2 c = this.diff(vector);
        return (c.getX() == 0 || c.getX() == -1 || c.getX() == 1) &&
                (c.getZ() == 0 || c.getZ() == -1 || c.getZ() == 1);
    }

    public static VectorI2 of(VectorI3 vector) {
        return new VectorI2(vector.getX(), vector.getZ());
    }

    public static VectorI2 of(int x, int z) {
        return new VectorI2(x, z);
    }

    public static VectorI2 minComponents(final VectorI2 A, final VectorI2 B) {
        return new VectorI2(Math.min(A.x, B.x), Math.min(A.z, B.z));
    }

    public static VectorI2 maxComponents(final VectorI2 A, final VectorI2 B) {
        return new VectorI2(Math.max(A.x, B.x), Math.max(A.z, B.z));
    }

    @Override
    public ConfigurationSection serialize() {
        ConfigurationSection section = new MemoryConfiguration();

        section.set("x", x);
        section.set("z", z);

        return section;
    }

    public static VectorI2 deserialize(ConfigurationSection section) {
        return new VectorI2(section.getInteger("x"),
                section.getInteger("z"));
    }

    @Override
    public String toString() {
        return "<" + x +
                ", " + z + "> [" + mag() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VectorI2)) return false;
        VectorI2 vectorI2 = (VectorI2) o;
        return x == vectorI2.x &&
                z == vectorI2.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
}

