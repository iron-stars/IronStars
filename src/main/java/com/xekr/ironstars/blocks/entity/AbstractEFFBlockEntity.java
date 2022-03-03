package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.efficiency.EFFNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AbstractEFFBlockEntity extends BlockEntity implements EFFMachine {
    private int lazyTickCounter = 0;

    @Nullable
    private EFFNetwork network = null;

    protected AbstractEFFBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void tick() {
        if (--this.lazyTickCounter <= 0) {
            this.lazyTickCounter = 10;
            lazyTick();
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void lazyTick() {
        BlockPos pos = this.getBlockPos();
        for (Direction direction : this.getOutputSide()) {
            BlockPos side = pos.relative(direction);
            BlockEntity blockEntity = this.level.getBlockEntity(side);
            if (blockEntity instanceof EFFMachine machine && machine.canInput(direction.getOpposite())) {
                EFFNetwork network = machine.getNetwork();
                if (network == null) machine.setNetwork(this.network);
                else if (!network.equals(this.network)) EFFNetwork.merge(this.network, network);
            }
        }
    }

    @Override
    public void load(@Nonnull CompoundTag nbt) {
        if (nbt.contains("uuid")) {
            EFFNetwork network = EFFNetwork.getNetwork(UUID.fromString(nbt.getString("uuid")));
            if (network != null && !network.equals(this.network))
                network.appendMachine(this);
        }
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(@Nonnull CompoundTag nbt) {
        if (this.network != null) nbt.putString("uuid", this.network.getId().toString());
    }

    @Nullable
    @Override
    public EFFNetwork getNetwork() {
        return this.network;
    }

    @Override
    public boolean hasNetwork() {
        return this.network != null;
    }

    @Override
    public void setNetwork(@Nullable EFFNetwork network) {
        this.network = network;
    }
}
