package com.altarisnine.redstone.common.command;

import com.altarisnine.redstone.api.command.CommandNode;
import com.altarisnine.redstone.api.command.argument.Argument;
import com.altarisnine.redstone.api.plugin.Plugin;
import com.altarisnine.redstone.common.RedstoneCore;
import com.google.common.collect.ImmutableList;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public abstract class CommandManager {
    protected RedstoneCore plugin;

    private HashMap<String, CommandNode> registeredCommands;
    private HashMap<String, String> registeredAliases;

    @Getter
    private List<String> available;

    public CommandManager(RedstoneCore instance) {
        this.plugin = instance;
        registeredCommands = new HashMap<>();
        registeredAliases = new HashMap<>();
        available = new ArrayList<>();
    }

    public final void registerCommand(CommandNode command, Plugin plugin) {
        register(command);
        registeredCommands.put(command.getName(), command);

        // Register aliases
        registerAliases(command);
    }

    public final void registerCommand(CommandNode command, RedstoneCore plugin) {
        register(command);
        registeredCommands.put(command.getName(), command);

        // Register aliases
        registerAliases(command);
    }

    private void registerAliases(CommandNode command) {
        // Register aliases
        for (String alias : command.getAliases()) {
            registeredAliases.put(alias, command.getName());
        }

        // Map main value as well
        registeredAliases.put(command.getName(), command.getName());
    }

    protected abstract void register(CommandNode command);

    public final List<String> getCompletions(String[] input, boolean console) {
        List<String> list = Arrays.asList(input);

        /*for (String s : list) {
            Redstone.getApi().getLogger().debug(s.replace(" ", "*"));
        }*/

        ListIterator<String> iterator = list.listIterator();

        CommandNode node;

        // Match command arg
        if (!iterator.hasNext()) {
            // return all command names
            return new ArrayList<>(registeredCommands.keySet());
        } else {
            final String cmdToTest = iterator.next();

            // Check for a complete match if there are more arguments, otherwise a partial match
            if (iterator.hasNext()) {
                String resolved = registeredAliases.get(cmdToTest);

                // If there are no matches, return no completions
                if (resolved == null) {
                    //Redstone.getApi().getLogger().debug("No matches for command with next argument provided");
                    return Collections.emptyList();
                }

                // Command node
                node = registeredCommands.get(resolved);
            } else {
                // PERFORMANCE would it be faster to just return matching values, if exact, only that one will be returned
                if (registeredAliases.keySet().stream().anyMatch(s -> s.equals(cmdToTest))) {
                    //Redstone.getApi().getLogger().debug("Alias matched, next tab complete will tackle next argument if user adds space");
                    return Collections.emptyList();
                } else {
                    //Redstone.getApi().getLogger().debug("Return matching aliases (if any)");
                    return registeredAliases.keySet().stream().filter(s -> s.startsWith(cmdToTest)).collect(Collectors.toList());
                }
            }
        }

        if (iterator.hasNext()) {
            subcommands:
            do {
                final String inputArg = iterator.next();

                // Iterate through children, look for match, continue while loop on a match
                for (CommandNode child : node.getChildren().values()) {
                    if (child.getAliases().contains(inputArg)) {
                        //Redstone.getApi().getLogger().debug("Matched child, update node cursor, and continue subcommands loop.");
                        node = child;
                        continue subcommands;
                    }
                }

                // If there is no child match, iterate through arguments
                // If no arguments match, complete to valid argument values and child names/aliases
                break subcommands;
            } while (iterator.hasNext());

            //Redstone.getApi().getLogger().debug("No more child matches, start matching arguments");

            iterator.previous();
        } else {
            return Arrays.asList("empty", "list", "or", "not", "three");
        }

        final int startIndex = iterator.nextIndex();

        // If this first-next argument is the last, then return child aliases as well as argument values

        if (iterator.hasNext()) {
            //Redstone.getApi().getLogger().debug("Found another input arg for argument checking");

            // Get input argument
            String inputArg = iterator.next();

            if (iterator.previousIndex() == startIndex && !iterator.hasNext()) {
                //Redstone.getApi().getLogger().debug("First non completed child node arg: " + inputArg);

                // Add children aliases to completions
                List<String> completions = new ArrayList<>(node.getChildren().keySet());

                int index = iterator.previousIndex() - startIndex;

                if (node.hasArgumentAt(index)) {
                    //Redstone.getApi().getLogger().debug("Found arguments for child, added to completions");
                    completions.addAll(node.getArgument(index).getAvailableValues());
                }

                if (!inputArg.equals(" ")) {
                    String finalInputArg1 = inputArg;
                    return completions.stream().filter(s -> s.startsWith(finalInputArg1)).collect(Collectors.toList());
                }
                else return completions;

            } else {
                iterator.previous();
                while (iterator.hasNext()) {
                    inputArg = iterator.next();

                    int index = iterator.previousIndex() - startIndex;

                    if (node.hasArgumentAt(index)) {
                        Argument<?> argument = node.getArgument(index);

                        //Redstone.getApi().getLogger().debug("Found argument #" + Integer.toString(index) + " with type " + argument.getClass().getSimpleName());

                        if (argument.validate(inputArg)) {
                            //Redstone.getApi().getLogger().debug(String.format("Validated argument #%d with input %s", index, inputArg));
                        } else {
                            if (!iterator.hasNext()) {
                                String finalInputArg = inputArg;
                                if (inputArg.equals(" ")) return argument.getAvailableValues();
                                else return argument.getAvailableValues().stream().filter(s -> s.startsWith(finalInputArg)).collect(Collectors.toList());
                            } else {
                                //Redstone.getApi().getLogger().debug("No arguments found that match, more arguments found, return no completions");
                                return Collections.emptyList();
                            }
                        }
                    }


                }
            }
        }

        return Collections.emptyList();
    }

    // Map all available base command names to immutable map for faster access
    public final void bake() {
        available = ImmutableList.copyOf(registeredCommands.keySet().stream().map(s -> '/' + s).collect(Collectors.toList()));
    }
}
