package com.altarisnine.redstone.api.command.argument;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.world.World;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public final class WorldArgument extends Argument<World> {

    @Getter @Setter private World value;

    public WorldArgument(String key) {
        super(key);
    }

    public WorldArgument(String key, boolean required) {
        super(key, required);
    }

    @Override
    public World parseArgument(String argument) throws ArgumentParseException {
        World world = Redstone.getApi().getWorld(argument);
        if (world != null) {
            return world;
        } else {
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        // TODO return available worlds
        return Redstone.getApi().getServer().getWorlds().stream().map(World::getName).collect(Collectors.toList());
    }

    @Override
    public String getErrorMessage() {
        return "That world cannot be found!";
    }
}
