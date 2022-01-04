package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MoonDust extends SnowLayerBlock {
    public MoonDust() {
        super(Block.Properties
                .of(Material.CLAY)
                .explosionResistance(0.5F)
                .sound(SoundType.SAND)
                .noOcclusion());
        this.setRegistryName("moon_dust");
    }

    @Override
    public VoxelShape getShape(BlockState p_56620_, BlockGetter p_56621_, BlockPos p_56622_, CollisionContext p_56623_) {
        return SHAPE_BY_LAYER[p_56620_.getValue(LAYERS)];
    }
}
