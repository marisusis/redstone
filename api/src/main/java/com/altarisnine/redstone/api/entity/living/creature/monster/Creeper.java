package com.altarisnine.redstone.api.entity.living.creature.monster;

import com.altarisnine.redstone.api.entity.living.creature.Monster;

public interface Creeper extends Monster {
    boolean isPowered();

    void setPowered(boolean powered);
}
