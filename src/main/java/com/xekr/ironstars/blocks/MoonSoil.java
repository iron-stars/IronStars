package com.xekr.ironstars.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MoonSoil extends Block {
    public MoonSoil() {
        super(Block.Properties
                .create(Material.CLAY)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.ROOT));
        this.setRegistryName("moon_soil");
    }
}
