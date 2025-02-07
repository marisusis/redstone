package com.altarisnine.redstone.api.entity.living.creature;

import com.altarisnine.redstone.api.entity.living.Creature;

public interface Ageable extends Creature {
    int getAge();

    void setAge(int age);

    void setLockAge(boolean lock);

    boolean getLockAge();

    void setBaby();

    void setAdult();

    boolean isAdult();

    boolean canBreed();

    void setBreed(boolean breed);
}
