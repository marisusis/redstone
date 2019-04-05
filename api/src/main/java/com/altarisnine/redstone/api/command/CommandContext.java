package com.altarisnine.redstone.api.command;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public final class CommandContext {

    private final Multimap<String, Object> parsedArguments;
    @Getter private String remaining;

    public CommandContext() {
        parsedArguments = ArrayListMultimap.create();
    }

    public <T> Optional<T> getOne(String key) {
        Collection<Object> values = this.parsedArguments.get(key);
        return (values.size() != 1) ? Optional.empty() : Optional.ofNullable((T) values.iterator().next());
    }

    public void putArg(String key, Object value) {
        if (value == null) throw new IllegalArgumentException();
        this.parsedArguments.put(key, value);
    }

    public void putRemaining(Iterator<String> remaining) {
        StringBuilder builder = new StringBuilder();

        while (remaining.hasNext()) {
            builder.append(" ");
            builder.append(remaining.next());
        }

        this.remaining = builder.toString();
    }
}