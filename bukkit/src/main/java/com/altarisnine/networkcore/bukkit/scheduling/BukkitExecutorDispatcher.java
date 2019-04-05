package com.altarisnine.networkcore.bukkit.scheduling;

import com.altarisnine.networkcore.common.scheduling.ExecutorDispatcher;
import org.bukkit.Bukkit;

public class BukkitExecutorDispatcher extends ExecutorDispatcher {
    private final BukkitNetworkScheduler scheduler;

    public BukkitExecutorDispatcher(BukkitNetworkScheduler scheduler) {
        super();
        this.scheduler = scheduler;
    }

    @Override
    public void execute(Runnable runnable) {
        if (Bukkit.isPrimaryThread())
            scheduler.getBukkitScheduler().runTaskAsynchronously(scheduler.getPlugin(), runnable);
        else
            runnable.run();
    }
}
