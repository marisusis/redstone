package com.altarisnine.redstone.api.entity.living.creature.golem;

import com.altarisnine.redstone.api.entity.living.creature.Golem;

public interface IronGolem extends Golem {
    boolean isPlayerCreated();

    void setPlayerCreated(boolean playerCreated);
}
