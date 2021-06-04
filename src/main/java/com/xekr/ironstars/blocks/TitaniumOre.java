package com.xekr.ironstars.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TitaniumOre extends Block {
    public TitaniumOre() {
        super(Block.Properties
                .create(Material.ROCK)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.STONE));
        this.setRegistryName("titanium_ore");
    }
}
