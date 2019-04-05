package com.altarisnine.redstone.api.block;

import com.altarisnine.redstone.api.world.Location;

public interface Block {

    Location getLocation();

    Material getType();

    void setType(Material material);


    /**
     * Helper methods to manipulate the block
     */
    void breakBlock();

}
