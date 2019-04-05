package com.altarisnine.networkcore.api.guard.spatial.boundary;

import com.altarisnine.networkcore.api.configuration.ConfigurationSection;
import com.altarisnine.networkcore.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.networkcore.api.configuration.serialization.DeserializationException;
import com.altarisnine.networkcore.api.guard.spatial.selection.Selector;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.world.World;

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
