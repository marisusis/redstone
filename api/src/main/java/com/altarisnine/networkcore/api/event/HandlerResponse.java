package com.altarisnine.networkcore.api.event;

import lombok.Getter;
import lombok.Setter;

public final class HandlerResponse {
    @Getter @Setter private boolean cancelled;

    public HandlerResponse(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
