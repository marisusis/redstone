package com.altarisnine.redstone.api.event;

public interface Cancellable {
    boolean isCancelled();

    void setCancelled(boolean cancel);
}
