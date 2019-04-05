package com.altarisnine.networkcore.bungee.scheduling;

import com.altarisnine.networkcore.common.scheduling.ExecutorDispatcher;

public class BungeeExecutorDispatcher extends ExecutorDispatcher {
    private BungeeNetworkScheduler scheduler;

    public BungeeExecutorDispatcher(BungeeNetworkScheduler scheduler) {
        super();
        this.scheduler = scheduler;
    }

    @Override
    public void execute(Runnable runnable) {
        scheduler.getBungeeScheduler().runAsync(scheduler.getPlugin(), runnable);
    }
}
