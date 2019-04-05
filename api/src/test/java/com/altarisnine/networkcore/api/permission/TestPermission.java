package com.altarisnine.networkcore.api.permission;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPermission {
    private static final Pattern parsing = Pattern.compile("^(?<sign>[+\\-])(?<path>(?:\\w+)(?:\\.\\w+)*)(?<wildcard>\\.\\*)?", Pattern.CASE_INSENSITIVE);


    // (?:\+|-)(\w+)(?:(\w*)\.)*(?:\*|(\w+))
    @Test
    public void testParse() {
        Matcher matcher = parsing.matcher("+core.guard.build.break.wool.*");
        matcher.find();
        final String path = matcher.group("path");

        System.out.format("Sign: %s, Node Path: %s, Wildcard: %s%n",
                matcher.group("sign"),
                path,
                matcher.group("wildcard") != null);

        System.out.println(Arrays.toString(path.split("\\.")));
    }

}
