package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class NetheritePressurePlateBlock extends AbstractPressurePlateBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public NetheritePressurePlateBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.FALSE));
    }

    @Override
    protected int getSignalForState(BlockState pState) {
        return pState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected  BlockState setSignalForState(BlockState pState, int pStrength) {
        return pState.setValue(POWERED, pStrength > 0);
    }

    @Override
    protected int getSignalStrength(Level pLevel, BlockPos pPos) {
        return this.entityOnPlate(pLevel, pPos, ItemEntity.class, entity ->
                !entity.getItem().getItem().canBeHurtBy(DamageSource.IN_FIRE)
        ) ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
    }
}
