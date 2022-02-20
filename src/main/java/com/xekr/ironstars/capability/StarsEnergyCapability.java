package com.xekr.ironstars.capability;

import com.xekr.ironstars.blocks.entity.AbstractEFFBlockEntity;
import com.xekr.ironstars.efficiency.EFFNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nonnull;

public class StarsEnergyCapability implements IStarsEnergyCapability {
//    /**
//     * 发电效率
//     * <p>总电量
//     */
//    private int genEfficiency = 0;
//    /**
//     * 剩余效率
//     * <p>当前电量
//     */
//    private int restEfficiency = 0;

    private final EFFNetwork network;

    public StarsEnergyCapability(@Nonnull EFFNetwork network) {
        this.network = network;
    }

    public int getGenEfficiency() {
        return this.network.getGenEfficiency();
    }

    public int getRestEfficiency() {
        return this.network.getRestEfficiency();
    }

    public boolean appendMachine(AbstractEFFBlockEntity blockEntity) {
        return this.network.appendMachine(blockEntity.getBlockPos(), blockEntity.getMachineEfficiency(), blockEntity.isSourceMachine());
    }

    public boolean appendMachine(BlockPos pos, int efficiency, boolean isSourceMachine) {
        return this.network.appendMachine(pos, efficiency, isSourceMachine);
    }

    public boolean removeMachine(BlockPos pos) {
        return this.network.removeMachine(pos);
    }

    public int getOutput(BlockPos pos) {
        return this.network.getOutput(pos);
    }
}
