package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MagnetBlock extends RotatedPillarBlock implements IWrenchBlock {
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

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        if (player != null && !world.isClientSide) {
            Direction.Axis target = switch (context.getClickedFace().getAxis()) {
                case X -> IWrenchBlock.rotateAxisX(state.getValue(AXIS));
                case Y -> IWrenchBlock.rotateAxisY(state.getValue(AXIS));
                case Z -> IWrenchBlock.rotateAxisZ(state.getValue(AXIS));
            };
            world.setBlockAndUpdate(context.getClickedPos(), state.setValue(AXIS, target));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
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
