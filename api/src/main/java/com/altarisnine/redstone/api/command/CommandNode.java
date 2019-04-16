package com.altarisnine.redstone.api.command;

import com.altarisnine.redstone.api.command.argument.Argument;
import com.altarisnine.redstone.api.command.argument.ArgumentParseException;
import com.altarisnine.redstone.api.command.argument.ChoiceArgument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.text.format.TextColor;
import com.altarisnine.redstone.api.util.Rank;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * A CommandNode
 */
public abstract class CommandNode implements Command {
    @Getter private final String name;

    @Getter private Map<String, CommandNode> children;

    @Getter @Setter private CommandNode parent;

    @Getter private final CommandArguments arguments;

    @Getter @Setter private Rank permissionLevel;

    @Getter private final List<String> aliases;

    private final String description;

    public CommandNode(String name, Rank permissionLevel, String description, List<String> aliases, final Argument<?>... arguments) {
        this(name, permissionLevel, description, aliases, Arrays.asList(arguments));
    }

    public CommandNode(String name, Rank permissionLevel, String description, List<String> aliases, final Collection<Argument<?>> arguments) {
        Objects.requireNonNull(description, "You must provide a description!");
        Objects.requireNonNull(name, "You must provide a name!");
        Objects.requireNonNull(permissionLevel, "You must provide a permission level!");
        Objects.requireNonNull(aliases, "Aliases list cannot be null");

        // Initialize map of children
        this.children = new HashMap<>();
        this.name = name;
        this.permissionLevel = permissionLevel;
        this.arguments = new CommandArguments();
        this.description = description;

        boolean prevRequired = true;

        // Handle specified arguments
        if (arguments != null) {
            // Validate arguments
            for (Argument argument : arguments) {
                if (!prevRequired && argument.isRequired()) throw new IllegalStateException("You cannot have an optional argument followed by a required argument!");
                prevRequired = argument.isRequired();
            }

            // Add all
            this.arguments.addAll(arguments);
        }

        // Add HelpCommand utility node
        if (!this.getClass().equals(HelpCommand.class) && !this.getClass().equals(GlobalHelpCommand.class)) {
            this.addChild(new HelpCommand());
        }


        this.aliases = ImmutableList.<String>builder().addAll(aliases).add(name).build();
    }

    public CommandNode(String name, Rank permissionLevel, String description) {
        this(name, permissionLevel, description, Collections.emptyList());
    }

    public CommandNode(String name, Rank permissionLevel, String description, Argument<?>... arguments) {
        this(name, permissionLevel, description, Collections.emptyList(), arguments);
    }

    public static <T, C extends T> C requireCast(T object, Class<C> castTo, String errorMessage) throws InternalCommandException {
        if (!castTo.isInstance(object)) {
            throw new InternalCommandException(Text.builder(errorMessage).color(TextColor.RED).build());
        } else return (C) object;
    }

    public static <T, C extends T> C requireCast(T object, Class<C> castTo, Text errorMessage) throws InternalCommandException {
        if (!castTo.isInstance(object)) {
            throw new InternalCommandException(errorMessage);
        } else return (C) object;
    }

    /**
     * A helper method to get a player object if the sender is one, and throw an exception sending a message to the sender if not a player.
     *
     * @param sender the sender
     * @return the player
     * @throws InternalCommandException the internal command exception
     */
    protected static Player requirePlayer(CommandSender sender) throws InternalCommandException {
        if (!(sender instanceof Player)) {
            throw new InternalCommandException(Text.builder("Only players can do this!").color(TextColor.RED).build());
        } else return (Player) sender;
    }

    protected static void requireTrue(boolean condition, String errorMessage) throws InternalCommandException {
        if (!condition) throw new InternalCommandException(Text.builder(errorMessage).color(TextColor.RED).build());
    }

    protected static void requireTrue(boolean condition, Text errorMessage) throws InternalCommandException {
        if (!condition) throw new InternalCommandException(errorMessage);
    }

    protected static void requireFalse(boolean condition, String errorMessage) throws InternalCommandException {
        if (condition) throw new InternalCommandException(Text.builder(errorMessage).color(TextColor.RED).build());
    }

    protected static void requireFalse(boolean condition, Text errorMessage) throws InternalCommandException {
        if (condition) throw new InternalCommandException(errorMessage);
    }

    protected static String requireRemaining(CommandContext context, String errorMessage) throws InternalCommandException {
        String remaining = context.getRemaining();
        if (remaining == null) throw new InternalCommandException(Text.builder(errorMessage).color(TextColor.RED).build());
        return remaining;
    }

    protected static String requireRemaining(CommandContext context, Text errorMessage) throws InternalCommandException {
        String remaining = context.getRemaining();
        if (remaining == null) throw new InternalCommandException(errorMessage);
        return remaining;
    }

    /**
     * Executes the command. This is where the command's logic should be put
     *
     * @param sender  the sender
     * @param context the context
     * @throws InternalCommandException the internal command exception
     */
    protected abstract void execute(CommandSender sender, CommandContext context) throws InternalCommandException;

    /**
     * Run the command. This is called internally to handle the sender and other variables before running {@link CommandNode#execute(CommandSender, CommandContext)}.
     *
     * @param sender the sender
     * @param args   the args
     * @see CommandNode#execute(CommandSender, CommandContext)
     */
    public final void process(CommandSender sender, List<String> args) {
        // Check if the sender has permission
        if (!sender.hasClearance(this.permissionLevel)) {
            sender.sendMessage(Text.builder("You are not allowed to do this! [Minimum Clearance Level: " + this.permissionLevel + ']').color(TextColor.RED).build());
            return;
        }
        // Select the current command node
        CommandNode selected = this;

        // Argument iterator
        ListIterator<String> inputArgs = args.listIterator();

        children: while (inputArgs.hasNext()) {
            final String inputArg = inputArgs.next();

            // Check if the selected command is a node
            if (!(selected instanceof CommandNode)) {
                return;
            }

            // Check if the node has any children that match the argument
            CommandNode child = selected.getChildren().get(inputArg);
            if (child != null) {
                // Select the matching child node
                selected = child;
            } else {
                // Check if an alias will match
                for (CommandNode value : selected.getChildren().values()) {
                    if (value.getAliases().stream().anyMatch(s -> s.equals(inputArg))) {
                        selected = value;
                        continue children;
                    }
                }

                // No more matches, move iterator back 1 step so loop includes non matching argument
                inputArgs.previous();
                break;
            }
        }

        // Handle all argument/command exceptions
        try {
            // Parse arguments
            CommandContext context = selected.arguments.parse(inputArgs);

            // Execute the command
            selected.execute(sender, context);
        } catch (ArgumentParseException e) {
            // Catch exceptions used for informative purposes (not coding errors (usually))
            sender.sendMessage(Text.builder(e.getMessage()).color(TextColor.RED).build());
        } catch (InternalCommandException e) {
            // Catch exceptions used for informative purposes (not coding errors (usually))
            sender.sendMessage(Text.builder(e.getFeedback()).build());
        }

    }

    /**
     * Add child.
     *
     * @param node the node
     */
    public void addChild(CommandNode node) {
        // Add node to list of children
        this.children.put(node.name, node);
        // Set the child node's parent to this node
        node.setParent(this);
    }

    public String getUsage() {
        StringBuilder result = new StringBuilder();

        CommandNode current = this;

        // Append name
        result.append(current.name);

        // Check for parents
        while (current.parent != null) {
            // Get parent
            current = current.parent;

            // Append name
            result.insert(0, " ");
            result.insert(0, current.name);

        }

        // Append command char
        result.insert(0, '/');


        for (Argument argument : this.arguments) {
            // Beginning of a required argument
            result.append(" <");
            // If the argument is a ChoiceArgument, use the choices instead of the key, separated by /
            if (argument instanceof ChoiceArgument) {
                final Iterator<String> iterator = ((ChoiceArgument) argument).getChoices().iterator();
                // Add the first choice
                result.append(iterator.next());
                while (iterator.hasNext()) {
                    // Add the remaining choices
                    result.append('/');
                    result.append(iterator.next());
                }
            } else {
                // For any other argument type, just use the key
                result.append(argument.getKey());
            }
            // End of a required argument
            result.append('>');
        }
        return result.toString();
    }

    public String getDescription() {
        return this.description;
    }

    public Argument<?> getArgument(int index) {
        return arguments.get(index);
    }

    public boolean hasArgumentAt(int index) {
        return index < arguments.size() && index >= 0;
    }
}
