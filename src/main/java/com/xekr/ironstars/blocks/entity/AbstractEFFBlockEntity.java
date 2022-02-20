package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.capability.StarsEnergyCapability;
import com.xekr.ironstars.efficiency.EFFNetwork;
import com.xekr.ironstars.registry.AllCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractEFFBlockEntity extends BlockEntity {
    private int lazyTickCounter = 0;

    @Nullable
    private EFFNetwork network = null;
    private final Set<Direction> sources = new HashSet<>();

    protected AbstractEFFBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void tick() {
        if (--this.lazyTickCounter <= 0) {
            this.lazyTickCounter = 10;
            lazyTick();
        }
    }

    public void lazyTick() {

    }

    @Override
    public void load(@Nonnull CompoundTag pTag) {
        super.load(pTag);
    }

    @Override
    protected void saveAdditional(@Nonnull CompoundTag pTag) {
    }

    @Nullable
    public EFFNetwork getNetwork() {
        return this.network;
    }

    public boolean hasNetwork() {
        return this.network != null;
    }

    public abstract boolean isSourceMachine();

    public abstract int getMachineEfficiency();

    public abstract Set<Direction> getOutputSide();

    public boolean canOutput(@Nonnull Direction side) {
        return !this.sources.contains(side) && !this.getOutputSide().contains(side);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (side != null && this.network != null && cap == AllCapabilities.STARS_ENERGY_CAPABILITY && this.canOutput(side))
            return LazyOptional.of(() -> new StarsEnergyCapability(this.network)).cast();
        return super.getCapability(cap, side);
    }
}
