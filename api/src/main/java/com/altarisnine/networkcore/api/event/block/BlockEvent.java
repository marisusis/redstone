package com.altarisnine.networkcore.api.event.block;

import com.altarisnine.networkcore.api.block.Block;
import com.altarisnine.networkcore.api.event.Event;
import lombok.Getter;

public abstract class BlockEvent extends Event {
    @Getter protected final Block block;

    protected BlockEvent(final Block theBlock) {
        this.block = theBlock;
    }
}
