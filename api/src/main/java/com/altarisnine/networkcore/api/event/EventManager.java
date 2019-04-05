package com.altarisnine.networkcore.api.event;

import com.altarisnine.networkcore.api.plugin.Plugin;

import java.util.Map;
import java.util.Set;

public interface EventManager {
    void registerHandlers(Listener listener, Plugin plugin);

    void callEvent(Event event);

    HandlerList getHandlers(Class<? extends Event> clazz);

    Map<Class<? extends Event>, Set<RegisteredListener>> makeRegisteredListeners(Listener listener, final Plugin plugin);
}
