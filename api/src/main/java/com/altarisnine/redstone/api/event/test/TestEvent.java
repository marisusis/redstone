package com.altarisnine.redstone.api.event.test;

import com.altarisnine.redstone.api.event.Event;
import com.altarisnine.redstone.api.event.HandlerList;

public final class TestEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final String message;

    public TestEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
