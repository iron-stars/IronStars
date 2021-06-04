package com.xekr.ironstars.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class Motor extends FurnaceBlock {
    public Motor() {
        super(Block.Properties
                .create(Material.IRON)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.METAL)
                .notSolid());
        this.setRegistryName("motor");
    }
}
