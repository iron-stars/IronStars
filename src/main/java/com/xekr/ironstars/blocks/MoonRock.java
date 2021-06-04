package com.xekr.ironstars.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MoonRock extends Block {
    public MoonRock() {
        super(Block.Properties
                .create(Material.IRON)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.STONE));
        this.setRegistryName("moon_rock");
    }
}
