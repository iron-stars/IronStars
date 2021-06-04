package com.xekr.ironstars.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ElectricWire extends Block {
    public ElectricWire() {
        super(Block.Properties
                .create(Material.IRON)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.METAL));
        this.setRegistryName("electric_wire");
    }
}
