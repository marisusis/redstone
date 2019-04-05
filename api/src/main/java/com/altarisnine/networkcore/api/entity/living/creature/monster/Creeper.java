package com.altarisnine.networkcore.api.entity.living.creature.monster;

import com.altarisnine.networkcore.api.entity.living.creature.Monster;

public interface Creeper extends Monster {
    boolean isPowered();

    void setPowered(boolean powered);
}
