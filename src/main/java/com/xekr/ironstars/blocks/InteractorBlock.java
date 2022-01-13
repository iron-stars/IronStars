package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class InteractorBlock extends Block {
    private static final VoxelShape SHAPE;

    public InteractorBlock(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    static {
        VoxelShape top = Block.box(2, 14, 2, 14, 16, 14);
        VoxelShape middle = Block.box(5, 14, 5, 10, 14, 10);
        VoxelShape bottom = Block.box(3, 3, 3, 13, 13, 13);
        SHAPE = Shapes.or(bottom, middle, top);
    }
}
