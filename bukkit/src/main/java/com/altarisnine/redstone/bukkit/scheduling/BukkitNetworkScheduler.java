package com.altarisnine.redstone.bukkit.scheduling;

import com.altarisnine.redstone.bukkit.BukkitRedstoneCore;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.scheduling.NetworkScheduler;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class BukkitNetworkScheduler extends NetworkScheduler {
    @Getter private BukkitScheduler bukkitScheduler;

    public BukkitNetworkScheduler(RedstoneCore instance) {
        super(instance);

        eventsExecutor = new BukkitExecutorDispatcher(this);
        databaseExecutor = new BukkitExecutorDispatcher(this);

        bukkitScheduler = getPlugin().getServer().getScheduler();
    }

    @Override
    public void runAsync(Runnable runnable) {
        bukkitScheduler.runTaskAsynchronously(getPlugin(), runnable);
    }

    @Override
    public void runSync(Runnable runnable) {
        bukkitScheduler.runTask(getPlugin(), runnable);
    }

    @Override
    public void runAsyncTaskTimer(Runnable runnable, long seconds) {
        bukkitScheduler.runTaskTimerAsynchronously(getPlugin(), runnable, 0L, seconds * 20L);
    }

    @Override
    public int scheduleTaskLater(Runnable runnable, long seconds) {
        int taskId = bukkitScheduler.runTaskLater(getPlugin(), runnable, seconds * 20L).getTaskId();
        getCurrentTasks().add(taskId);
        return taskId;
    }

    @Override
    public void cancelTask(int id) {
        bukkitScheduler.cancelTask(id);
        getCurrentTasks().add(id);
    }

    public Plugin getPlugin() {
        return ((BukkitRedstoneCore) plugin).getBootstrap();
    }
}
