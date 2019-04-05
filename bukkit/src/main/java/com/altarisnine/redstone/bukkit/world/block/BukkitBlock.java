package com.altarisnine.redstone.bukkit.world.block;

import com.altarisnine.redstone.api.block.Material;
import com.altarisnine.redstone.api.world.Location;
import com.altarisnine.redstone.bukkit.Converter;
import com.altarisnine.redstone.common.block.CoreBlock;
import lombok.Getter;
import org.bukkit.block.Block;

public class BukkitBlock extends CoreBlock {

    @Getter private final Block block;
    @Getter private final Material type;

    public BukkitBlock(Block block) {
        this.block = block;
        this.type = Converter.blockType(block.getType());
    }

    @Override
    public Location getLocation() {
        return Converter.location(block.getLocation());
    }

    @Override
    public void setType(Material material) {
        block.setType(Converter.blockType(material));
    }

    @Override
    public void breakBlock() {
        block.breakNaturally();
    }
}
