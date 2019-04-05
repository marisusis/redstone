package com.altarisnine.redstone.api.command;

import com.altarisnine.redstone.api.command.argument.ChoiceArgument;
import com.altarisnine.redstone.api.command.exception.InternalCommandException;
import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class CommandTest {

    static CommandNode moveCommand = Command.builder("move")
            .description("Move somewhere")
            .argument(new ChoiceArgument("direction", "up", "down"))
            .executor((sender, context) -> {
               sender.sendMessage(Text.of("Now moving %s", context.<String>getOne("direction")));
            }).build();

    final static CommandSender testSender = new CommandSender() {
        @Override
        public boolean hasClearance(Rank rank) {
            System.out.format("Clearance check: %s%n", rank);
            return true;
        }

        @Override
        public void sendMessage(String message) {
            System.out.format("CommandSender: %s%n", message);
        }

        @Override
        public void sendMessage(Text text) {
            sendMessage(text.getPlaintext());
        }
    };


    @Test
    void testExecution() {
        new ActionCommand().process(testSender, Arrays.asList("move", "down"));
        new ActionCommand().process(testSender, Collections.singletonList("h"));
    }

    private static class ActionCommand extends CommandNode {

        public ActionCommand() {
            super("action", Rank.DEFAULT, "are an test command");
            addChild(moveCommand);
        }

        @Override
        protected void execute(CommandSender sender, CommandContext context) throws InternalCommandException {
            sender.sendMessage(Text.of("ACTION TIME!"));
        }
    }


}
