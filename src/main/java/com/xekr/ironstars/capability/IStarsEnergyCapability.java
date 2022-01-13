package com.xekr.ironstars.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IStarsEnergyCapability extends INBTSerializable<CompoundTag> {
    /**
     * 获取发电效率
     */
    int getGenEfficiency();

    /**
     * 获取剩余效率
     */
    int getRestEfficiency();

    /**
     * 增加效率
     * <p>只有发电机才会增加效率
     */
    void add(int amount);

    /**
     * 减少效率
     */
    void reduce(int amount);

    /**
     * 获取输出效率
     * <p>如果效率不足, 降低效率输出
     * @param expect 期待效率
     * @return 实际效率
     */
    int getOutput(int expect);
}
