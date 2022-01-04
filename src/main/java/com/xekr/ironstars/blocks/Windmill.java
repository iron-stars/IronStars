package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class Windmill extends Block {
    public Windmill() {
        super(Block.Properties
                .of(Material.METAL)
                .explosionResistance(0.5F)
                .sound(SoundType.METAL));
        this.setRegistryName("windmill");
    }
}
