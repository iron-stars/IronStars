package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MoonDustBlock extends SnowLayerBlock {
    public MoonDustBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_56620_, BlockGetter p_56621_, BlockPos p_56622_, CollisionContext p_56623_) {
        return SHAPE_BY_LAYER[p_56620_.getValue(LAYERS)];
    }
}
