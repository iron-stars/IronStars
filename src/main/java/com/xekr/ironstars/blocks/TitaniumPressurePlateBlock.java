package com.xekr.ironstars.blocks;

import com.xekr.ironstars.blocks.entity.TitaniumPressurePlateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class TitaniumPressurePlateBlock extends AbstractPressurePlateBlock implements EntityBlock {
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    public TitaniumPressurePlateBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PRESSED, false).setValue(POWER, 0));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(PRESSED) ? PRESSED_AABB : AABB;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PRESSED, POWER);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRand) {
        if (pState.getValue(PRESSED)) this.checkPressed(null, pLevel, pPos, pState, true);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide && !pState.getValue(PRESSED)) checkPressed(pEntity, pLevel, pPos, pState, false);
    }

    @Override
    protected int getSignalStrength(Level pLevel, BlockPos pPos) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof TitaniumPressurePlateBlockEntity) {
            return ((TitaniumPressurePlateBlockEntity) blockEntity).getOutput();
        }
        return 0;
    }

    @Override
    protected int getSignalForState(BlockState pState) {
        return pState.getValue(POWER);
    }

    @Override
    protected BlockState setSignalForState(BlockState pState, int pStrength) {
        return pState.setValue(POWER, pStrength);
    }

    protected void checkPressed(@Nullable Entity pEntity, Level pLevel, BlockPos pPos, BlockState pState, boolean currentPressed) {
        boolean pressed = this.entityOnPlate(pLevel, pPos);
        this.checkPressed(pEntity, pLevel, pPos, pState, currentPressed, pressed, PRESSED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TitaniumPressurePlateBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null : (world, pos, state, blockEntity) -> {
            if (blockEntity instanceof TitaniumPressurePlateBlockEntity) ((TitaniumPressurePlateBlockEntity)blockEntity).tick();
        };
    }
}
