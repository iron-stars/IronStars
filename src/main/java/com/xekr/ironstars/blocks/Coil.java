package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class Coil extends RotatedPillarBlock {
    public Coil(){
        super(Block.Properties
                .of(Material.METAL)
                .explosionResistance(0.5f)
                .sound(SoundType.METAL));
        this.setRegistryName("coil");
    }
}
