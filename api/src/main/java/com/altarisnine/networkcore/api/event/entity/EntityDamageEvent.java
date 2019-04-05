package com.altarisnine.networkcore.api.event.entity;

import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.event.Cancellable;
import com.altarisnine.networkcore.api.event.HandlerList;
import lombok.Getter;
import lombok.Setter;

public class EntityDamageEvent extends EntityEvent implements Cancellable {
    @Getter
    private static final HandlerList handlerList = new HandlerList();

    @Getter @Setter private boolean cancelled;
    @Getter private final double damage;
    @Getter private final DamageCause cause;

    public EntityDamageEvent(Entity which, double damage, DamageCause cause) {
        super(which);
        this.damage = damage;
        this.cause = cause;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
