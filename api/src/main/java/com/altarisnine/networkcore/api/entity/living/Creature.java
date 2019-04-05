package com.altarisnine.networkcore.api.entity.living;

import com.altarisnine.networkcore.api.entity.LivingEntity;

public interface Creature extends LivingEntity {
    void setTarget(LivingEntity target);

    LivingEntity getTarget();
}
