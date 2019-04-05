package com.altarisnine.redstone.api.entity.living;

import com.altarisnine.redstone.api.entity.Entity;

public interface Tameable extends Entity {
    void setTamed(boolean tamed);

    boolean isTamed();
}
