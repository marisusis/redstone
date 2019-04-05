package com.altarisnine.redstone.api.command;

import com.altarisnine.redstone.api.command.argument.Argument;
import com.altarisnine.redstone.api.command.argument.ArgumentParseException;

import java.util.ArrayList;
import java.util.ListIterator;

public class CommandArguments extends ArrayList<Argument<?>> {
    public CommandContext parse(ListIterator<String> inputIter) throws ArgumentParseException {
        CommandContext context = new CommandContext();

        for (Argument argument : this) {
            // Get the next argument
            // Check existence and validate
            if (!inputIter.hasNext()) {
                if (argument.isRequired()) {
                    throw new ArgumentParseException("Improper usage!");
                } else {
                    continue;
                }
            }

            String next = inputIter.next();

            // Add the parsed argument to the CommandContext
            context.putArg(argument.getKey(), argument.parseArgument(next));
        }

        // Add the remaining arguments to the CommandContext
        context.putRemaining(inputIter);

        // Return the CommandContext
        return context;
    }
}
