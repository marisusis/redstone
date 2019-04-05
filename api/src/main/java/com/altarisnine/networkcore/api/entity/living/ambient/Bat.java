package com.altarisnine.networkcore.api.entity.living.ambient;

import com.altarisnine.networkcore.api.entity.Entity;

public interface Bat extends Entity {
    void setAwake(boolean awake);

    boolean isAwake();
}
