package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;

public class Turbine extends FurnaceBlock {
    public Turbine() {
        super(Block.Properties
                .of(Material.METAL)
                .explosionResistance(0.5F)
                .sound(SoundType.METAL)
                .noOcclusion());
        this.setRegistryName("turbine");
    }
}
