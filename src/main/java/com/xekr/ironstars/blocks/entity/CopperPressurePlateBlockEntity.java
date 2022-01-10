package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.blocks.CopperPressurePlateBlock;
import com.xekr.ironstars.registry.AllBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.xekr.ironstars.blocks.CopperPressurePlateBlock.POWERED;

public class CopperPressurePlateBlockEntity extends BlockEntity {
    private long times;
    public CopperPressurePlateBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AllBlockEntities.COPPER_PRESSURE_PLATE.get(), pWorldPosition, pBlockState);
    }

    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            BlockState state = this.getBlockState();
            boolean powered = state.getValue(POWERED);
            if (state.getValue(CopperPressurePlateBlock.PRESSED)) {
                times++;
                if (powered) updatePower(false);
            }else {
                if (times > 0) {
                    if (!powered) updatePower(true);
                    times--;
                } else if (powered) updatePower(false);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void updatePower(boolean pValue) {
        this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(POWERED, pValue));
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
