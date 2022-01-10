package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.blocks.TitaniumPressurePlateBlock;
import com.xekr.ironstars.registry.AllBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.xekr.ironstars.blocks.TitaniumPressurePlateBlock.POWER;

public class TitaniumPressurePlateBlockEntity extends BlockEntity {
    private long times;
    public TitaniumPressurePlateBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AllBlockEntities.TITANIUM_PRESSURE_PLATE.get(), pWorldPosition, pBlockState);
    }

    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            BlockState state = this.getBlockState();
            int power = state.getValue(POWER);
            if (state.getValue(TitaniumPressurePlateBlock.PRESSED)) {
                this.times++;
                if (power > 0) updatePower(0);
            }else {
                int output = this.getOutput();
                if (power != output) updatePower(output);
                if (this.times > 0) this.times--;
            }
        }
    }

    public int getOutput() {
        return Math.min(Mth.ceil(this.times / 20.0F), 15);
    }

    @SuppressWarnings("ConstantConditions")
    private void updatePower(int pValue) {
        this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(POWER, pValue));
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.times = pTag.getLong("Times");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putLong("Times", this.times);
    }
}
