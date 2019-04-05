package com.altarisnine.redstone.api.entity.living.ambient;

import com.altarisnine.redstone.api.entity.Entity;

public interface Bat extends Entity {
    void setAwake(boolean awake);

    boolean isAwake();
}
