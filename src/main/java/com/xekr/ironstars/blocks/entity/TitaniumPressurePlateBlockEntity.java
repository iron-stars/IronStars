package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.blocks.TitaniumPressurePlateBlock;
import com.xekr.ironstars.registry.AllBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import static com.xekr.ironstars.blocks.TitaniumPressurePlateBlock.POWERED;

public class TitaniumPressurePlateBlockEntity extends BlockEntity {
    public static long times;
    public TitaniumPressurePlateBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AllBlockEntities.TITANIUM_PRESSURE_PLATE.get(), pWorldPosition, pBlockState);
    }

    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            BlockState state = this.getBlockState();
            boolean powered = state.getValue(POWERED);
            if (state.getValue(TitaniumPressurePlateBlock.PRESSED)) {
                times++;
                if (powered) {
                    updateState(POWERED, false);
                }
            }else {
                if (times > 0) {
                    if (!powered) {
                        updateState(POWERED, true);
                    }
                    times--;
                } else if (powered) {
                    updateState(POWERED, false);
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
        times = pTag.getLong("Times");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putLong("Times", times);
    }
}
