package com.xekr.ironstars.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TitaniumAlloyBlock extends Block {
    public TitaniumAlloyBlock() {
        super(Block.Properties
                .create(Material.IRON)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.METAL));
        this.setRegistryName("titanium_alloy_block");
    }
}
