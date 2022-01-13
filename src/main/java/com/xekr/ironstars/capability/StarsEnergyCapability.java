package com.xekr.ironstars.capability;

import net.minecraft.nbt.CompoundTag;

public class StarsEnergyCapability implements IStarsEnergyCapability {
    /**
     * 发电效率
     * <p>总电量
     */
    private int genEfficiency = 0;
    /**
     * 剩余效率
     * <p>当前电量
     */
    private int restEfficiency = 0;

    public int getGenEfficiency() {
        return this.genEfficiency;
    }

    public int getRestEfficiency() {
        return this.restEfficiency;
    }

    public void add(int amount) {
        this.genEfficiency += amount;
        this.restEfficiency += amount;
    }

    public void reduce(int amount) {
        this.restEfficiency -= amount;
    }

    public int getOutput(int expect) {
        return expect;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("genEfficiency", this.genEfficiency);
        nbt.putInt("restEfficiency", this.restEfficiency);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.genEfficiency = nbt.getInt("genEfficiency");
        this.restEfficiency = nbt.getInt("restEfficiency");
    }
}
