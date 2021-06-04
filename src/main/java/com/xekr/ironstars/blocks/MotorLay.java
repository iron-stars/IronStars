package com.xekr.ironstars.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class MotorLay extends FurnaceBlock {
    public MotorLay() {
        super(Block.Properties
                .create(Material.IRON)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.METAL)
                .notSolid());
        this.setRegistryName("motor_lay");
    }
}
