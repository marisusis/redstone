package com.altarisnine.redstone.api.scheduling;

public interface Scheduler {
    void runAsync(Runnable runnable);
    void runSync(Runnable runnable);

    void runAsyncTaskTimer(Runnable runnable, long seconds);
    int scheduleTaskLater(Runnable runnable, long seconds);

    void cancelTask(int id);
}
