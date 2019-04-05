package com.altarisnine.networkcore.api.entity.living.creature.monster;

import com.altarisnine.networkcore.api.block.Material;
import com.altarisnine.networkcore.api.entity.living.creature.Monster;

public interface Enderman extends Monster {
    Material getCarrying();

    void setCarrying(Material material);
}
