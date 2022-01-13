package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoilBlock extends RotatedPillarBlock {
    private static final VoxelShape SHAPE;

    public CoilBlock(Properties properties){
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    static {
        VoxelShape top = Block.box(0, 0, 0, 16, 16, 16);
        VoxelShape middle = Block.box(2, 2, 2, 14, 14, 14);
        VoxelShape bottom = Block.box(0, 0, 0, 16, 2, 16);
        SHAPE = Shapes.or(bottom, middle, top);
    }
}
