package com.xekr.ironstars.capability;

import com.xekr.ironstars.blocks.entity.AbstractEFFBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IStarsEnergyCapability {
    /**
     * 获取发电效率
     */
    int getGenEfficiency();

    /**
     * 获取剩余效率
     */
    int getRestEfficiency();

    /**
     * 新增机器
     */
    boolean appendMachine(AbstractEFFBlockEntity blockEntity);

    /**
     * 新增机器
     * @param isSourceMachine 是否发电机
     */
    boolean appendMachine(BlockPos pos, int efficiency, boolean isSourceMachine);

    /**
     * 移除机器
     */
    boolean removeMachine(BlockPos pos);

    /**
     * 获取输出效率
     */
    int getOutput(BlockPos pos);

}
