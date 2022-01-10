package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MotorBlock extends Block {
//    public static final BooleanProperty LAY = BooleanProperty.create("lay");
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private static final VoxelShape axShape;
    private static final VoxelShape azShape;
    private static final VoxelShape bxShape;
    private static final VoxelShape bzShape;
    private final boolean lay;

    public MotorBlock(Properties properties, boolean lay) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
        this.lay = lay;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(AXIS, context.getHorizontalDirection().getAxis());
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(AXIS)) {
            case X -> this.lay ? bxShape : axShape;
            case Z -> this.lay ? bzShape : azShape;
            case Y -> throw new IllegalStateException(pState.getBlock() + " dont have Y direction");
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(AXIS);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(AXIS)) {
                case Z -> state.setValue(AXIS, Direction.Axis.X);
                case X -> state.setValue(AXIS, Direction.Axis.Z);
                default -> state;
            };
            default -> state;
        };
    }

    static {
        VoxelShape bottom = Block.box(0, 0, 0, 16, 2, 16);
        VoxelShape middle = Block.box(1, 2, 1, 15, 14, 15);
        VoxelShape top = Block.box(0, 14, 0, 16, 16, 16);
        VoxelShape port = Block.box(5, 2, 0, 11, 3, 16);
        VoxelShape side = Block.box(0, 6, 4, 16, 10, 12);
        axShape = Shapes.or(bottom, middle, top, port, side);
        port = Block.box(0, 2, 5, 16, 3, 11);
        side = Block.box(4, 6, 0, 12, 10, 16);
        azShape = Shapes.or(bottom, middle, top, port, side);
        middle = Block.box(1, 2, 0, 15, 15, 16);
        top = Block.box(4, 15, 6, 12, 16, 10);
        bzShape = Shapes.or(bottom, middle, top, port);
        middle = Block.box(0, 2, 1, 16, 15, 15);
        top = Block.box(6, 15, 4, 10, 16, 12);
        port = Block.box(5, 2, 0, 11, 3, 16);
        bxShape = Shapes.or(bottom, middle, top, port);
    }
}
