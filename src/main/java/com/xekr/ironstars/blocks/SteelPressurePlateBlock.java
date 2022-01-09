package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

public class SteelPressurePlateBlock extends CopperPressurePlateBlock implements EntityBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private final SteelPressurePlateBlock.Sensitivity sensitivity;

    public SteelPressurePlateBlock(Sensitivity pSensitivity, BlockBehaviour.Properties pProperties){
        super(pSensitivity, pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.valueOf(false)));
        this.sensitivity = pSensitivity;
    }
    @Override
    protected int getSignalForState(BlockState pState) {
        return pState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected BlockState setSignalForState(BlockState pState, int pStrength) {
        return (BlockState)pState.setValue(POWERED, Boolean.valueOf(pStrength > 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }
}
