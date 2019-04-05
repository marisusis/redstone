package com.altarisnine.redstone.api.entity;

public interface Damageable {
    void damage(double damage);

    void damage(double amount, Entity source);

    void setHealth(double health);

    double getHealth();

    void kill();
}
