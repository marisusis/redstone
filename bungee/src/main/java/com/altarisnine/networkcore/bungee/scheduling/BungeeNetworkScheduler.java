package com.altarisnine.networkcore.bungee.scheduling;

import com.altarisnine.networkcore.bungee.BungeeNetworkCore;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.scheduling.NetworkScheduler;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.TaskScheduler;

import java.util.concurrent.TimeUnit;

public class BungeeNetworkScheduler extends NetworkScheduler {

    @Getter private TaskScheduler bungeeScheduler;

    public BungeeNetworkScheduler(NetworkCore instance) {
        super(instance);

        databaseExecutor = new BungeeExecutorDispatcher(this);
        eventsExecutor = new BungeeExecutorDispatcher(this);
        configExecutor = new BungeeExecutorDispatcher(this);

        bungeeScheduler = getPlugin().getProxy().getScheduler();
    }

    @Override
    public void shutdown() {
        super.shutdown();
        bungeeScheduler.cancel(getPlugin());
            }

    @Override
    public void runAsync(Runnable runnable) {
        bungeeScheduler.runAsync(getPlugin(), runnable);
    }

    @Override
    public void runSync(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void runAsyncTaskTimer(Runnable runnable, long seconds) {
        bungeeScheduler.schedule(getPlugin(), runnable, 0L, seconds, TimeUnit.SECONDS);
    }

    @Override
    public int scheduleTaskLater(Runnable runnable, long seconds) {
        int taskId = bungeeScheduler.schedule(getPlugin(), runnable, 0L, seconds, TimeUnit.SECONDS).getId();
        getCurrentTasks().add(taskId);
        return taskId;
    }

    @Override
    public void cancelTask(int id) {
        bungeeScheduler.cancel(id);
        super.getCurrentTasks().remove(id);
    }

    public Plugin getPlugin() {
        return ((BungeeNetworkCore) plugin).getBootstrap();
    }
}
