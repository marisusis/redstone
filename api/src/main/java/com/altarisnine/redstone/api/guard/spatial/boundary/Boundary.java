package com.altarisnine.redstone.api.guard.spatial.boundary;

import com.altarisnine.redstone.api.configuration.ConfigurationSection;
import com.altarisnine.redstone.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.redstone.api.configuration.serialization.DeserializationException;
import com.altarisnine.redstone.api.guard.spatial.selection.Selector;
import com.altarisnine.redstone.api.guard.spatial.vector.VectorI3;
import com.altarisnine.redstone.api.world.World;

public interface Boundary extends ConfigSerializable {

    double getArea();

    double getVolume();

    boolean isWithin(final VectorI3 v);

    @Deprecated
    VectorI3[] getVertices();

    Selector getSelector();

    World getWorld();

    static Boundary deserialize(ConfigurationSection section) {
        final String type = section.getString("type");
        if (type.equalsIgnoreCase("cubic")) {
            return CubicBoundary.deserialize(section);
        } else if (type.equalsIgnoreCase("column")){
            return ColumnBoundary.deserialize(section);
        } else {
            throw new DeserializationException("Cannot deserialize boundary of unknown type!");
        }
    }
}
