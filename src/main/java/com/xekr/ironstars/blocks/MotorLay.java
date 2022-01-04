package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;

public class MotorLay extends FurnaceBlock {
    public MotorLay() {
        super(Block.Properties
                .of(Material.METAL)
                .explosionResistance(0.5F)
                .sound(SoundType.METAL)
                .noOcclusion());
        this.setRegistryName("motor_lay");
    }
}
