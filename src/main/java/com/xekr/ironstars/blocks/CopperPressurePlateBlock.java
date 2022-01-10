package com.xekr.ironstars.blocks;

import com.xekr.ironstars.blocks.entity.CopperPressurePlateBlockEntity;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CopperPressurePlateBlock extends AbstractPressurePlateBlock implements EntityBlock {
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public CopperPressurePlateBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false).setValue(PRESSED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(PRESSED) ? PRESSED_AABB : AABB;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PRESSED, POWERED);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRand) {
        if (pState.getValue(PRESSED)) {
            this.checkPressed(null, pLevel, pPos, pState, true);
        }
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide && !pState.getValue(PRESSED)) {
            checkPressed(pEntity, pLevel, pPos, pState, false);
        }
    }

    @Override
    protected int getSignalStrength(Level pLevel, BlockPos pPos) {
        return this.entityOnPlate(pLevel, pPos) ? 15 : 0;
    }

    @Override
    protected int getSignalForState(BlockState pState) {
        return pState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected BlockState setSignalForState(BlockState pState, int pStrength) {
        return pState.setValue(POWERED, pStrength > 0);
    }

    protected void checkPressed(@Nullable Entity pEntity, Level pLevel, BlockPos pPos, BlockState pState, boolean currentPressed) {
        boolean pressed = this.getSignalStrength(pLevel, pPos) > 0;
        this.checkPressed(pEntity, pLevel, pPos, pState, currentPressed, pressed, PRESSED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CopperPressurePlateBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null : (world, pos, state, blockEntity) -> {
            if (blockEntity instanceof CopperPressurePlateBlockEntity) {
                ((CopperPressurePlateBlockEntity)blockEntity).tick();
            }
        };
    }
}
