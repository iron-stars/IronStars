package com.xekr.ironstars.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MoonDust extends SnowBlock {
    public MoonDust() {
        super(Block.Properties
                .create(Material.CLAY)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.SAND));
        this.setRegistryName("moon_dust");
    }
}
