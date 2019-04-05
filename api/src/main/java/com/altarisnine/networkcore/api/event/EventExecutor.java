package com.altarisnine.networkcore.api.event;

@FunctionalInterface
public interface EventExecutor {
    void execute(Listener listener, Event event);
}
