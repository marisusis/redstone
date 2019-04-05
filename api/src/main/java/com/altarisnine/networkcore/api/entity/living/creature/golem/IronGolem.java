package com.altarisnine.networkcore.api.entity.living.creature.golem;

import com.altarisnine.networkcore.api.entity.living.creature.Golem;

public interface IronGolem extends Golem {
    boolean isPlayerCreated();

    void setPlayerCreated(boolean playerCreated);
}
