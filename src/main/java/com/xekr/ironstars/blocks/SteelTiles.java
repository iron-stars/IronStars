package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SteelTiles extends Block {
    public SteelTiles() {
        super(Block.Properties
                .of(Material.STONE)
                .explosionResistance(0.5F)
                .sound(SoundType.STONE));
        this.setRegistryName("steel_tiles");
    }
}
