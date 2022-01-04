package com.xekr.ironstars.blocks;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SiliconBlock extends Block {
    public SiliconBlock() {
        super(Block.Properties
                .of(Material.METAL)
                .explosionResistance(0.5F)
                .sound(SoundType.METAL));
        this.setRegistryName("silicon_block");
    }
}