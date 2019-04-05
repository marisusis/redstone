package com.altarisnine.networkcore.common.scheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class ExecutorDispatcher implements Executor {
    private ExecutorService networkExecutor;

    protected ExecutorDispatcher() {
        networkExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable command) {
        networkExecutor.submit(command);
    }

    void shutdown() {
        try {
            networkExecutor.shutdown();
            networkExecutor.awaitTermination(10L, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {}
        finally {
            networkExecutor.shutdownNow();
        }

    }
}
