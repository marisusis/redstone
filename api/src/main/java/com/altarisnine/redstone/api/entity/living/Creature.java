package com.altarisnine.redstone.api.entity.living;

import com.altarisnine.redstone.api.entity.LivingEntity;

public interface Creature extends LivingEntity {
    void setTarget(LivingEntity target);

    LivingEntity getTarget();
}
