package com.altarisnine.networkcore.api.entity.living.creature.animal;

public interface Parrot extends Animal {
    Parrot.Variant getVariant();

    void setVariant(Parrot.Variant variant);

    enum Variant {
        RED,
        BLUE,
        GREEN,
        CYAN,
        GRAY
    }
}
