package com.altarisnine.networkcore.common.scheduling;

import com.altarisnine.networkcore.api.scheduling.Scheduler;
import com.altarisnine.networkcore.common.NetworkCore;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public abstract class NetworkScheduler implements Scheduler {
    protected NetworkCore plugin;

    @Getter protected ExecutorDispatcher databaseExecutor;
    @Getter protected ExecutorDispatcher eventsExecutor;
    @Getter protected ExecutorDispatcher configExecutor;

    @Getter protected Set<Integer> currentTasks;

    protected NetworkScheduler(NetworkCore instance) {
        plugin = instance;
        currentTasks = new HashSet<>();
    }

    public void shutdown() {
        databaseExecutor.shutdown();
    }

    public abstract void runAsync(Runnable runnable);
    public abstract void runSync(Runnable runnable);

    public abstract void runAsyncTaskTimer(Runnable runnable, long seconds);
    public abstract int scheduleTaskLater(Runnable runnable, long seconds);

    public abstract void cancelTask(int id);
}
