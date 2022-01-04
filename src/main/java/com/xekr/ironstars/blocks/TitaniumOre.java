package com.xekr.ironstars.blocks;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class TitaniumOre extends Block {
    public TitaniumOre() {
        super(Block.Properties
                .of(Material.STONE)
                .explosionResistance(0.5F)
                .sound(SoundType.STONE));
        this.setRegistryName("titanium_ore");
    }
}
