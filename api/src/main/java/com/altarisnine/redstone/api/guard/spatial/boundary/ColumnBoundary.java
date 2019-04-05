package com.altarisnine.redstone.api.guard.spatial.boundary;

import com.altarisnine.redstone.api.configuration.ConfigurationSection;
import com.altarisnine.redstone.api.configuration.objects.MemoryConfiguration;
import com.altarisnine.redstone.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.redstone.api.guard.spatial.selection.ColumnSelector;
import com.altarisnine.redstone.api.guard.spatial.selection.Selector;
import com.altarisnine.redstone.api.guard.spatial.vector.VectorI2;
import com.altarisnine.redstone.api.guard.spatial.vector.VectorI3;
import com.altarisnine.redstone.api.world.World;
import lombok.Getter;

public class ColumnBoundary extends AbstractBoundary implements ConfigSerializable {
    @Getter private VectorI2 min, max;

    public ColumnBoundary(World world, VectorI2 A, VectorI2 B) {
        super(world);
        this.min = VectorI2.minComponents(A, B);
        this.max = VectorI2.maxComponents(A, B);
    }

    public ColumnBoundary(World world, VectorI3 A, VectorI3 B) {
        this(world, A.toBlockVector2(), B.toBlockVector2());
    }

    public void updatePoints(VectorI2 A, VectorI2 B) {
        min = VectorI2.minComponents(A, B);
        max = VectorI2.maxComponents(A, B);
    }

    @Override
    public double getArea() {
        int l = min.getX() - max.getX();
        int w = min.getZ() - max.getZ();

        return (l * w);
    }

    @Override
    public double getVolume() {
        // 255 is build limit, so limit solid height to 255
        return getArea() * 255;
    }

    @Override
    public boolean isWithin(VectorI3 v) {
        // We only consider the x and z axises because the boundary is infinite on the y axis
        return (min.getX() <= v.getX()) && (v.getX() <= max.getX())
                && (min.getZ() <= v.getZ()) && (v.getZ() <= max.getZ());
    }

    @Override
    public VectorI3[] getVertices() {
        return new VectorI3[]{
                VectorI3.of(min, 0),
                VectorI3.of(max, 255)
        };
    }

    @Override
    public Selector getSelector() {
        ColumnSelector selector = new ColumnSelector();
        selector.selectPrimary(min.toBlockVector3());
        selector.selectSecondary(max.toBlockVector3());
        return selector;
    }

    @Override
    public ConfigurationSection serialize() {
        ConfigurationSection section = new MemoryConfiguration();

        section.set("type", "column");
        section.set("min", min);
        section.set("max", max);

        return section;
    }

    public static ColumnBoundary deserialize(ConfigurationSection section) {
        return new ColumnBoundary(section.get(World.class, "world"), section.get(VectorI2.class, "min"),
                section.get(VectorI2.class, "max"));
    }
}
