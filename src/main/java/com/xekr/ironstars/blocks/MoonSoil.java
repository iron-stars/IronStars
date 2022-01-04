package com.xekr.ironstars.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class MoonSoil extends Block {
    public MoonSoil() {
        super(Block.Properties
                .of(Material.CLAY)
                .explosionResistance(0.5F)
                .sound(SoundType.ROOTS));
        this.setRegistryName("moon_soil");
    }
}
