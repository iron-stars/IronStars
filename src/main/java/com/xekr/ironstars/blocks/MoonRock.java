package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class MoonRock extends Block {
    public MoonRock() {
        super(Block.Properties
                .of(Material.METAL)
                .explosionResistance(0.5F)
                .sound(SoundType.STONE));
        this.setRegistryName("moon_rock");
    }
}
