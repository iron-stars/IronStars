package com.xekr.ironstars.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MagnetBlock extends RotatedPillarBlock {
    public MagnetBlock() {
        super(Block.Properties
                .create(Material.IRON)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.METAL)
                .notSolid());
        this.setRegistryName("magnet_block");
    }
}
