package com.altarisnine.networkcore.api.guard.spatial.boundary;

import com.altarisnine.networkcore.api.configuration.ConfigurationSection;
import com.altarisnine.networkcore.api.configuration.objects.MemoryConfiguration;
import com.altarisnine.networkcore.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.networkcore.api.guard.spatial.selection.Selector;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.world.World;
import lombok.Getter;
import lombok.Setter;

public final class CubicBoundary extends AbstractBoundary implements ConfigSerializable {

    @Getter @Setter private VectorI3 A, B;

    public CubicBoundary(World world, VectorI3 A, VectorI3 B) {
        super(world);
        this.A = A;
        this.B = B;
    }

    @Override
    public double getArea() {
        // Get the length and width
        double l = A.getX() - B.getX();
        double w = A.getZ() - B.getZ();

        // Area cannot be negative
        return Math.abs(l * w);
    }

    @Override
    public double getVolume() {
        // Get the height
        double h = A.getY() - B.getY();

        // Volume cannot be negative
        return Math.abs(getArea() * h);
    }

    @Override
    public boolean isWithin(VectorI3 v) {
        VectorI3 max = VectorI3.maxComponents(A, B);
        VectorI3 min = VectorI3.minComponents(A, B);

        return (min.getX() <= v.getX()) && (v.getX() <= max.getX())
                && (min.getY() <= v.getY()) && (v.getY() <= max.getY())
                && (min.getZ() <= v.getZ()) && (v.getZ() <= max.getZ());
    }

    @Override
    public VectorI3[] getVertices() {
        return new VectorI3[]{A, B};
    }

    @Override
    public Selector getSelector() {
        return null; // TODO cubic selector
    }

    @Override
    public ConfigurationSection serialize() {
        ConfigurationSection section = new MemoryConfiguration();

        section.set("type", "cubic");
        section.set("A", A);
        section.set("B", B);

        return section;
    }

    public static CubicBoundary deserialize(ConfigurationSection section) {
        return new CubicBoundary(section.get(World.class, "world"), section.get(VectorI3.class, "A"),
                section.get(VectorI3.class, "B"));
    }
}
