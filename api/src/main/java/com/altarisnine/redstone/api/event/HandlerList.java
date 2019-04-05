package com.altarisnine.redstone.api.event;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class HandlerList {

    private volatile RegisteredListener[] listeners;
    private final EnumMap<EventPriority, ArrayList<RegisteredListener>> handlers;

    public HandlerList() {
        handlers = new EnumMap<>(EventPriority.class);
        for (EventPriority value : EventPriority.values()) {
            handlers.put(value, new ArrayList<>());
        }
    }

    public synchronized void register(RegisteredListener listener) {
        handlers.get(listener.getPriority()).add(listener);
        listeners = null;
    }

    public void registerAll(Iterable<RegisteredListener> listeners) {
        for (RegisteredListener listener : listeners) {
            register(listener);
        }
    }

    public synchronized RegisteredListener[] getListeners() {
        List<RegisteredListener> entries = new ArrayList<>();

        if (listeners == null) {
            for (Map.Entry<EventPriority, ArrayList<RegisteredListener>> entry : handlers.entrySet()) {
                entries.addAll(entry.getValue());
            }

            listeners = entries.toArray(new RegisteredListener[0]);
        }

        return listeners;
    }
}
