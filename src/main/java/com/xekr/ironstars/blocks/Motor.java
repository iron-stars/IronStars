package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;

public class Motor extends FurnaceBlock {
    public Motor() {
        super(Block.Properties
                .of(Material.METAL)
                .explosionResistance(0.5F)
                .sound(SoundType.METAL)
                .noOcclusion());
        this.setRegistryName("motor");
    }
}
