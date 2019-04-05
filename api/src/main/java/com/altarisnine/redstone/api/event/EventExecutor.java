package com.altarisnine.redstone.api.event;

@FunctionalInterface
public interface EventExecutor {
    void execute(Listener listener, Event event);
}
