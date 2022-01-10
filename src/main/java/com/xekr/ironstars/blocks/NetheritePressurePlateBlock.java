package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NetheritePressurePlateBlock extends BasePressurePlateBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public NetheritePressurePlateBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.FALSE));
    }

    @Override
    protected int getSignalForState(BlockState pState) {
        return pState.getValue(POWERED) ? 15 : 0;
    }

    @NotNull
    @Override
    protected  BlockState setSignalForState(BlockState pState, int pStrength) {
        return pState.setValue(POWERED, pStrength > 0);
    }

    @Override
    protected void playOnSound(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.playSound(null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);
    }

    @Override
    protected void playOffSound(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.playSound(null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.5F);
    }

    @Override
    protected int getSignalStrength(Level pLevel, BlockPos pPos) {
        List<? extends Entity> list = pLevel.getEntitiesOfClass(ItemEntity.class, TOUCH_AABB.move(pPos), entity ->
                !entity.getItem().getItem().canBeHurtBy(DamageSource.IN_FIRE)
        );

        for(Entity entity : list) {
            if (!entity.isIgnoringBlockTriggers()) {
                return 15;
            }
        }

        return 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
    }
}
