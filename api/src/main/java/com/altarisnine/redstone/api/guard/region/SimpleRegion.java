package com.altarisnine.redstone.api.guard.region;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.configuration.ConfigurationSection;
import com.altarisnine.redstone.api.configuration.serialization.ConfigSerializable;
import com.altarisnine.redstone.api.guard.Guard;
import com.altarisnine.redstone.api.guard.flag.Flag;
import com.altarisnine.redstone.api.guard.flag.FlagNotFoundException;
import com.altarisnine.redstone.api.guard.spatial.boundary.Boundary;

public class SimpleRegion<B extends Boundary> extends FlaggedRegion<B> implements ConfigSerializable {
    public SimpleRegion(String name, B boundary) {
        super(name, boundary);
    }

    @Override
    public ConfigurationSection serialize() {
        ConfigurationSection section = ConfigurationSection.createBlank();

        section.set("name", name);
        section.set("boundary", boundary);
        ConfigurationSection flags = section.createSection("flags");

        this.flags.forEach((flag, obj) -> {
            ConfigurationSection flagSection = flags.createSection(flag.getName());
            flagSection.set("name", flag.getName());
            flagSection.set("value", queryFlagState(flag).toString());
        });

        return section;
    }

    public static SimpleRegion<?> deserialize(ConfigurationSection section) {
        // TODO different types of boundaries should be specified in regions file (ie. type: cuboid or type: column)
        // FIXME parse different boundary types

        Boundary boundary = section.get(Boundary.class, "boundary");

        Guard guard = Redstone.getApi().getGuard();

        SimpleRegion<?> region = new SimpleRegion<>(section.getString("name"), boundary);

        ConfigurationSection flags = section.getSection("flags");
        flags.getValues(false).forEach((key, value) -> {
            // Get flag section from file
            ConfigurationSection flagSection = flags.getSection(key);

            // Get flag name and value
            String flagName = flagSection.getString("name");
            String flagValue = flagSection.getString("value");

            // Attempt to parse flag and value, then apply to region
            try {
                Flag flag = guard.getFlagByName(flagName);
                region.setFlag(flag, flag.parseValue(flagValue));
            } catch (FlagNotFoundException e) {
                throw new IllegalStateException("Flag " + flagName + " not found!");
            }
        });

        return region;
    }
}
