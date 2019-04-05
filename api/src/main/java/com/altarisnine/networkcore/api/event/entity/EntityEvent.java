package com.altarisnine.networkcore.api.event.entity;

import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.event.Event;
import lombok.Getter;

public abstract class EntityEvent extends Event {
    @Getter private Entity entity;

    protected EntityEvent(Entity which) {
        this.entity = which;
    }
}
