package com.xekr.ironstars.fluids;

import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class RedstoneFluid extends ForgeFlowingFluid {
    protected RedstoneFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSource(FluidState pState) {
        return false;
    }

    @Override
    public int getAmount(FluidState pState) {
        return 0;
    }
}