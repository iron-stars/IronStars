package com.xekr.ironstars.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class Turbine extends FurnaceBlock {
    public Turbine() {
        super(Block.Properties
                .create(Material.IRON)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.METAL)
                .notSolid());
        this.setRegistryName("turbine");
    }
}
