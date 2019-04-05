package com.altarisnine.networkcore.api.block;

import com.altarisnine.networkcore.api.world.Location;

public interface Block {

    Location getLocation();

    Material getType();

    void setType(Material material);


    /**
     * Helper methods to manipulate the block
     */
    void breakBlock();

}
