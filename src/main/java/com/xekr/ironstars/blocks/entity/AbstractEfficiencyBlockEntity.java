package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.capability.StarsEnergyCapability;
import com.xekr.ironstars.registry.AllCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated //TODO 先写着, 写完再去掉
public abstract class AbstractEfficiencyBlockEntity extends BlockEntity {
    private int lazyTickCounter = 0;

    @Nullable private BlockPos source;

    protected AbstractEfficiencyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void tick() {
        if (this.lazyTickCounter-- == 0) {
            this.lazyTickCounter = 10;
            lazyTick();
        }
    }

    public void lazyTick() {

    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
    }

    public abstract int getGeneratedEfficiency();

    public abstract boolean canOutput(@Nullable Direction side);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == AllCapabilities.STARS_ENERGY_CAPABILITY && canOutput(side))
            return LazyOptional.of(StarsEnergyCapability::new).cast();
        return super.getCapability(cap, side);
    }
}
