package com.altarisnine.redstone.common.scheduling;

import com.altarisnine.redstone.api.scheduling.Scheduler;
import com.altarisnine.redstone.common.RedstoneCore;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public abstract class NetworkScheduler implements Scheduler {
    protected RedstoneCore plugin;

    @Getter protected ExecutorDispatcher databaseExecutor;
    @Getter protected ExecutorDispatcher eventsExecutor;
    @Getter protected ExecutorDispatcher configExecutor;

    @Getter protected Set<Integer> currentTasks;

    protected NetworkScheduler(RedstoneCore instance) {
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
