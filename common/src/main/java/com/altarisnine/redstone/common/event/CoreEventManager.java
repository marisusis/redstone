package com.altarisnine.redstone.common.event;

import com.altarisnine.redstone.api.event.EventListener;
import com.altarisnine.redstone.api.event.*;
import com.altarisnine.redstone.api.plugin.EventBrokeException;
import com.altarisnine.redstone.api.plugin.Plugin;
import com.altarisnine.redstone.common.RedstoneCore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CoreEventManager implements EventManager {

    private final RedstoneCore core;

    public CoreEventManager(RedstoneCore instance) {
        this.core = instance;
    }



    @Override
    public void registerHandlers(Listener listener, Plugin plugin) {
        // TODO Check if core is enabled first

        for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : makeRegisteredListeners(listener, plugin).entrySet()) {
            getHandlers(entry.getKey()).registerAll(entry.getValue());
        }
    }

    @Override
    public void callEvent(Event event) {
        if (event.isAsynchronous()) {
            if (Thread.holdsLock(this)) {
                throw new IllegalStateException(event.getClass().getName() + " cannot be process asynchronously");
            }

            fireEvent(event);
        } else {
            synchronized (this) {
                fireEvent(event);
            }
        }
    }

    private void fireEvent(Event event) {
        HandlerList handlers = event.getHandlers();
        RegisteredListener[] listeners = handlers.getListeners();

        for (RegisteredListener listener : listeners) {
            listener.callEvent(event);
        }
    }

    @Override
    public HandlerList getHandlers(Class<? extends Event> clazz) {
        try {
            Method method = clazz.getDeclaredMethod("getHandlerList");
            method.setAccessible(true);
            return (HandlerList) method.invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new EventBrokeException(e.getMessage());
        }
    }

    @Override
    public Map<Class<? extends Event>, Set<RegisteredListener>> makeRegisteredListeners(Listener listener, Plugin plugin) {
        Map<Class<? extends Event>, Set<RegisteredListener>> result = new HashMap<>();

        Method[] methods = listener.getClass().getDeclaredMethods();

        for (Method method : methods) {

            // Get the EventListener annotation from the method
            final EventListener eventListener = method.getAnnotation(EventListener.class);

            // Skip the method if the annotation doesn't exist
            if (eventListener == null) continue;

            if (method.isBridge() || method.isSynthetic()) {
                continue;
            }

            Class<?> aClass = null;
            if ((method.getParameterTypes().length != 1) || !Event.class.isAssignableFrom(aClass = method.getParameterTypes()[0])) {
                core.getLogger().error("Unable to register listener " + method.getName() + " from " + listener.getClass().getName());
            }

            final Class<? extends Event> eventClass = aClass.asSubclass(Event.class);
            method.setAccessible(true);

            Set<RegisteredListener> eventSet = result.computeIfAbsent(eventClass, k -> new HashSet<>());

            EventExecutor executor = (listener1, event) -> {
                try {
                    if (!eventClass.isAssignableFrom(event.getClass())) {
                        core.getLogger().debug("not assignable from entity class");
                        return;
                    }

                    method.invoke(listener1, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            };

            Objects.requireNonNull(listener, "listener");
            Objects.requireNonNull(eventListener, "elistener");
            Objects.requireNonNull(executor, "exec");
            Objects.requireNonNull(plugin, "core");
            Objects.requireNonNull(eventSet, "set");

            eventSet.add(new RegisteredListener(listener, eventListener.priority(), executor, plugin));
        }

        return result;
    }
}
