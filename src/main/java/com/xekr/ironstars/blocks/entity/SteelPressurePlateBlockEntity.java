package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.blocks.SteelPressurePlateBlock;
import com.xekr.ironstars.registry.IBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import static net.minecraft.world.level.block.PressurePlateBlock.POWERED;

public class SteelPressurePlateBlockEntity extends BlockEntity {
    public SteelPressurePlateBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(IBlockEntities.STEEL_PRESSURE_PLATE.get(), pWorldPosition, pBlockState);
    }

    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            BlockState state = this.getBlockState();
            if (state.getValue(SteelPressurePlateBlock.PRESSED)) {
                updateState(POWERED, true);
            } else {
                updateState(POWERED, false);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private <T extends Comparable<T>, V extends T> void updateState(Property<T> pProperty, V pValue) {
        this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(pProperty, pValue));
    }
}