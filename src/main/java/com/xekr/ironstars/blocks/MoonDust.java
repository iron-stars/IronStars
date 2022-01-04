package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class MoonDust extends SnowLayerBlock {
    public MoonDust() {
        super(Block.Properties
                .of(Material.CLAY)
                .explosionResistance(0.5F)
                .sound(SoundType.SAND)
                .noOcclusion());
        this.setRegistryName("moon_dust");
    }
}
