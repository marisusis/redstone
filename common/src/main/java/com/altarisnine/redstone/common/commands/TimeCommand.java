package com.altarisnine.redstone.common.commands;

import com.altarisnine.redstone.api.command.Command;
import com.altarisnine.redstone.api.command.CommandContext;
import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.CommandSender;
import com.altarisnine.redstone.api.command.argument.TimeArgument;
import com.altarisnine.redstone.api.command.argument.TimeIntervalArgument;
import com.altarisnine.redstone.api.command.argument.WorldArgument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;
import com.altarisnine.redstone.api.util.Time;
import com.altarisnine.redstone.api.world.World;

import java.util.Optional;

public class TimeCommand extends CommandNode {

    public TimeCommand() {
        super("time", Rank.SRMOD, "Modify or query the time");
        addChild(Command.builder("set")
        .alias("s")
        .description("Set the time")
        .argument(new TimeArgument("time"))
        .argument(new WorldArgument("world", false))
        .executor((sender, context) -> {
            // Get modifier value
            Time time = context.<Time>getOne("time").get();

            // Get optional world
            Optional<World> optWorld = context.getOne("world");

            World world;

            if (!optWorld.isPresent()) {
                Player player = CommandNode.requirePlayer(sender);
                world = player.getLocation().getWorld();
            } else {
                world = optWorld.get();
            }

            world.setTime(time);

            sender.sendMessage(Text.of("&3The time is now &e%s &3in world &e%s", time.toString(), world.getName()));
        })
        .build());

        addChild(Command.builder("add")
        .alias("s")
        .description("Increase the world time")
        .argument(new TimeIntervalArgument("time"))
        .argument(new WorldArgument("world", false))
        .executor((sender, context) -> {
            // Get modifier value
            Time time = context.<Time>getOne("time").get();

            // Get optional world
            Optional<World> optWorld = context.getOne("world");

            World world;

            if (!optWorld.isPresent()) {
                Player player = CommandNode.requirePlayer(sender);
                world = player.getLocation().getWorld();
            } else {
                world = optWorld.get();
            }

            world.setTime(time.add(world.getTime()));

            sender.sendMessage(Text.of("&3The time is now &e%s &3in world &e%s", world.getTime().add(time), world.getName()));
        })
        .build());
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        // Get action
        String action = context.<String>getOne("action").get();

        // Get modifier value
        Time time = context.<Time>getOne("time").get();

        // Get optional world
        Optional<World> optWorld = context.getOne("world");

        // Initialize world field
        World world;

        if (!optWorld.isPresent()) {
            Player player = CommandNode.requirePlayer(sender);
            world = player.getLocation().getWorld();
        } else {
            world = optWorld.get();
        }

        // Apply action to world
        if (action.equals("add")) {
            world.setTime(world.getTime().add(time));
        } else if (action.equals("set")) {
            world.setTime(time);
        }
    }

}
