package com.altarisnine.networkcore.common.commands.guard.region;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.command.*;
import com.altarisnine.networkcore.api.command.argument.ChoiceArgument;
import com.altarisnine.networkcore.api.command.argument.FlagArgument;
import com.altarisnine.networkcore.api.command.argument.RegionArgument;
import com.altarisnine.networkcore.api.command.argument.StringArgument;
import com.altarisnine.networkcore.api.command.exception.InternalCommandException;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.guard.Guard;
import com.altarisnine.networkcore.api.guard.flag.Flag;
import com.altarisnine.networkcore.api.guard.region.Region;
import com.altarisnine.networkcore.api.guard.region.SimpleRegion;
import com.altarisnine.networkcore.api.guard.spatial.boundary.Boundary;
import com.altarisnine.networkcore.api.guard.spatial.selection.IncompleteSelectionException;
import com.altarisnine.networkcore.api.guard.spatial.selection.Selector;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;

public class RegionCommand extends CommandNode {

    private final CommandNode regionWand = Command.builder("wand")
            .description("Acquire a region wand")
            .argument(new ChoiceArgument("wandType", "select", "resize"))
            .executor((sender, context) -> {
                Player player = requirePlayer(sender);

                switch (context.<String>getOne("wandType").get()) {
                    case "select":
                        player.getInventory().addItem(Guard.SELECT_WAND_ITEM);
                        break;
                    case "resize":
                        player.getInventory().addItem(Guard.RESIZE_WAND_ITEM);
                        break;
                }
            }).build();

    private final CommandNode createRegion = Command.builder("create")
            .description("Create a region")
            .rank(Rank.ADMIN)
            .argument(new StringArgument("regionName"))
            .executor((sender, context) -> {
                Player player = CommandNode.requirePlayer(sender);

                // Get player's selector
                Selector selector = player.getSelector();

                // Get the region's name
                String name = context.<String>getOne("name").get();

                if (Core.getApi().getGuard().getRegion(name) != null) {
                    player.sendMessage(Text.of("&cThat region already exists!"));
                    return;
                }

                try {
                    // Get boundary from player selection
                    Boundary boundary = selector.getBoundary();

                    // Create region from parameters
                    SimpleRegion<? extends Boundary> newRegion = new SimpleRegion<>(name, boundary);

                    // Register the region
                    Core.getApi().getGuard().registerRegion(newRegion);
                } catch (IncompleteSelectionException e) {
                    player.sendMessage(Text.of("&cYou must make a valid selection!"));
                }

            }).build();

    private final CommandNode listRegions = Command.builder("list")
            .rank(Rank.MOD)
            .description("List all regions")
            .executor((sender, context) -> {
                Player player = CommandNode.requirePlayer(sender);

                StringBuilder builder = new StringBuilder();

                builder.append("&2--w- &aREGIONS &2---\n");

                for (Region region : Core.getApi().getGuard().getRegions(player.getLocation().getWorld())) {
                    builder.append(String.format("&c%s&4: &cWithin: %s", region.getName(), region.isWithin(player)));
                    builder.append("\n");
                }

                sender.sendMessage(Text.of(builder.toString()));
            }).build();

    private final CommandNode flagRegion = Command.builder("flag")
            .rank(Rank.ADMIN)
            .description("Modify region flags")
            .argument(new RegionArgument("region"))
            .argument(new FlagArgument("flag"))
            .argument(new StringArgument("state"))
            .executor(new CommandExecutor() {
                @Override
                public void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
                    Region target = context.<Region>getOne("region").get();
                    Flag flag = context.<Flag>getOne("flag").get();
                    sender.sendMessage(Text.of("&6Applying flag &a%s &6to &a%s&6...", target.getName(), flag.getName()));

                    String value = context.<String>getOne("state").get();

                    target.setFlag(flag,flag.parseValue(value));
                }
            }).build();

    private final CommandNode selectRegion = Command.builder("select")
            .description("Select a region")
            .argument(new RegionArgument("region"))
            .executor((sender, context) -> {
                Player player = CommandNode.requirePlayer(sender);

                // Get region
                Region region = context.<Region>getOne("region").get();

                // Select region
                player.select(region.getBoundary());

                // TODO give feedback
            }).build();

    public RegionCommand() {
        super("region", Rank.DEFAULT, "Base region command");
        addChild(regionWand);
        addChild(createRegion);
        addChild(listRegions);
        addChild(flagRegion);
        addChild(selectRegion);
    }

    @Override
    protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
        sender.sendMessage(Text.of("&cRegion &6command"));
    }
}
