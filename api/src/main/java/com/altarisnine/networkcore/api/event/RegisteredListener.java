package com.altarisnine.networkcore.api.event;

import com.altarisnine.networkcore.api.plugin.Plugin;
import lombok.Getter;

public class RegisteredListener {
    @Getter private final Listener listener;
    @Getter private final EventPriority priority;
    @Getter private final Plugin plugin;
    private final EventExecutor executor;


    public RegisteredListener(Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin) {
        this.listener = listener;
        this.priority = priority;
        this.plugin = plugin;
        this.executor = executor;
    }

    public void callEvent(final Event event) {
        //Core.getApi().getLogger().debug("Attempting to call entity [" + entity.getClass().getName() + "]");
        executor.execute(listener, event);
    }
}
