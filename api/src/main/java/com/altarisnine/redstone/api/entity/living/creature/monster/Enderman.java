package com.altarisnine.redstone.api.entity.living.creature.monster;

import com.altarisnine.redstone.api.block.Material;
import com.altarisnine.redstone.api.entity.living.creature.Monster;

public interface Enderman extends Monster {
    Material getCarrying();

    void setCarrying(Material material);
}
