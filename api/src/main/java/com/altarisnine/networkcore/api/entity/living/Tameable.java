package com.altarisnine.networkcore.api.entity.living;

import com.altarisnine.networkcore.api.entity.Entity;

public interface Tameable extends Entity {
    void setTamed(boolean tamed);

    boolean isTamed();
}
