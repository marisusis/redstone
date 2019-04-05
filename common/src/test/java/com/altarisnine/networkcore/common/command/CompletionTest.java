package com.altarisnine.networkcore.common.command;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CompletionTest {
    private final String input = "/region info glo";
    private final String input2 = "/command info glowing ";
    private final String input3 = "/ regn info glo";
    private final String input4 = "/ ";

    @Test
    public void test1() {

        process(input);
        process(input2);
        process(input3);
        process(input4);

    }

    public void process(String input) {
        String[] inputs = input.replaceAll("/ ( *)/", " ").split(" ");

        System.out.format("Array: %s%n", Arrays.toString(inputs));

        String command = inputs[0].substring(1);

        System.out.format("Command portion: %s%n", command);

        for (int i = 1; i < inputs.length; i++) {
            System.out.format("Arg #%d: %s%n", i, inputs[i]);

            // Match argument
        }

        List<String> completions;
    }

    public void log(String s) {
        System.out.format("TEST: %s%n", s);
    }
}
