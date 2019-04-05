package com.altarisnine.networkcore.api.event.guard.region;

import com.altarisnine.networkcore.api.event.Event;
import com.altarisnine.networkcore.api.event.HandlerList;
import com.altarisnine.networkcore.api.guard.region.RegionSet;
import com.altarisnine.networkcore.api.guard.session.Session;
import lombok.Getter;

public class RegionEnterEvent extends Event {
    @Getter
    private static final HandlerList handlerList = new HandlerList();

    @Getter private final Session session;
    @Getter private final RegionSet entered;

    public RegionEnterEvent(Session session, RegionSet entered) {
        super();
        this.session = session;
        this.entered = entered;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
