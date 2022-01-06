package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MagnetBlock extends RotatedPillarBlock {
    private static final VoxelShape xShape;
    private static final VoxelShape yShape;
    private static final VoxelShape zShape;

    public MagnetBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> xShape;
            case Y -> yShape;
            case Z -> zShape;
        };
    }

    static {
        VoxelShape reduce = Block.box(0, 5, 5, 16, 11, 11);
        xShape = Shapes.join(Shapes.block(), reduce, BooleanOp.NOT_SAME);
        reduce = Block.box(5, 0, 5, 11, 16, 11);
        yShape = Shapes.join(Shapes.block(), reduce, BooleanOp.NOT_SAME);
        reduce = Block.box(5, 5, 0, 11, 11, 16);
        zShape = Shapes.join(Shapes.block(), reduce, BooleanOp.NOT_SAME);
    }
}
