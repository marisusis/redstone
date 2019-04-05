package com.altarisnine.redstone.api.event.entity;

import com.altarisnine.redstone.api.entity.Entity;
import com.altarisnine.redstone.api.event.Event;
import lombok.Getter;

public abstract class EntityEvent extends Event {
    @Getter private Entity entity;

    protected EntityEvent(Entity which) {
        this.entity = which;
    }
}
