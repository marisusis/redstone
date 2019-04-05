package com.altarisnine.networkcore.api.event;

import lombok.Getter;

/**
 * The base class for all events.
 */
public abstract class Event {

    @Getter private final boolean asynchronous;

    /**
     * Instantiates a new event, asynchronous is false by default
     */
    protected Event() {
        this(false);
    }

    /**
     * Instantiates a new event, optionally async.
     *
     * @param async the async
     */
    protected Event(boolean async) {
        this.asynchronous = async;
    }

    /**
     * Get the handlers for this event
     *
     * @return the handlers
     */
    public abstract HandlerList getHandlers();
}