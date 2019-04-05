package com.altarisnine.redstone.api.command;

import com.altarisnine.redstone.api.command.argument.Argument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.util.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Command {

    void process(CommandSender sender, List<String> args);
    String getName();
    String getUsage();
    String getDescription();
    List<String> getAliases();
    CommandNode getParent();
    void setParent(CommandNode node);

    static Builder builder() {
        return new Builder();
    }

    static Builder builder(String name) {
        return new Builder(name);
    }

    final class Builder {

        private String name;
        private String description = "DEFAULT BUILDER DESCRIPTION";
        private CommandArguments arguments;
        private CommandExecutor executor;
        private Rank permission = Rank.DEFAULT;
        private List<String> aliases;

        Builder() {
            this.arguments = new CommandArguments();
            this.aliases = new ArrayList<>();
        }

        Builder(String name) {
            this();
            this.name = name;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder argument(Argument<?> argument) {
            arguments.add(argument);
            return this;
        }

        public Builder executor(CommandExecutor executor) {
            this.executor = executor;
            return this;
        }

        public Builder alias(String alias) {
            this.aliases.add(alias);
            return this;
        }

        public Builder aliases(String... aliases) {
            this.aliases.addAll(Arrays.asList(aliases));
            return this;
        }

        public Builder rank(Rank rank) {
            this.permission = rank;
            return this;
        }

        public CommandNode build() {
            return new BuiltCommand(this.name, this.description, this.permission, this.arguments, this.executor, this.aliases);
        }


    }


    class BuiltCommand extends CommandNode {

        private final CommandExecutor executor;

        BuiltCommand(String name, String description, Rank permission, CommandArguments arguments, CommandExecutor executor, List<String> aliases) {
            super(name, permission, description, aliases, arguments);
            this.executor = executor;
        }

        @Override
        protected final void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
            executor.execute(sender, context);
        }
    }


}
