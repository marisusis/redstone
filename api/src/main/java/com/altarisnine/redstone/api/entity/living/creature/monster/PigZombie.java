package com.altarisnine.redstone.api.entity.living.creature.monster;

public interface PigZombie extends Zombie {
    int getAnger();

    void setAnger(int level);

    void setAngry(boolean angry);

    boolean isAngry();
}
