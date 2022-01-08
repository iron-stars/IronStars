package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.blocks.CopperPressurePlateBlock;
import com.xekr.ironstars.registry.IBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class CopperPressurePlateBlockEntity extends BlockEntity {
    private long times;
    public CopperPressurePlateBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(IBlockEntities.COPPER_PRESSURE_PLATE.get(), pWorldPosition, pBlockState);
    }

    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            BlockState state = this.getBlockState();
            boolean powered = state.getValue(CopperPressurePlateBlock.POWERED);
            boolean pressed = state.getValue(CopperPressurePlateBlock.PRESSED);
            if (((CopperPressurePlateBlock)state.getBlock()).entityOn(this.level, this.getBlockPos())) {
                times++;
                if (!pressed) updateState(CopperPressurePlateBlock.PRESSED, true);
                if (powered) updateState(CopperPressurePlateBlock.POWERED, false);
            }else {
                if (pressed) updateState(CopperPressurePlateBlock.PRESSED, false);
                if (times > 0) {
                    if (!powered) updateState(CopperPressurePlateBlock.POWERED, true);
                    times--;
                } else {
                    if (powered) updateState(CopperPressurePlateBlock.POWERED, false);
                }
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private <T extends Comparable<T>, V extends T> void updateState(Property<T> pProperty, V pValue) {
        this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(pProperty, pValue));
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.times = pTag.getLong("Times");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putLong("Times", times);
    }
}
