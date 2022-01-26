package com.xekr.ironstars.blocks;

import com.xekr.ironstars.registry.AllTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class TitaniumAlloyPressurePlateBlock extends AbstractPressurePlateBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public TitaniumAlloyPressurePlateBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
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
                entity.getItem().is(Items.NETHER_STAR) || AllTags.Items.TITANIUM_ALLOY.contains(entity.getItem().getItem())
        ) ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
    }
}
