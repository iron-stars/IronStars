package com.xekr.ironstars.blocks.entity;

import com.xekr.ironstars.efficiency.EFFNetwork;
import net.minecraft.core.Direction;

import javax.annotation.Nullable;
import java.util.Set;

public interface EFFMachine {
    @Nullable
    EFFNetwork getNetwork();

    void setNetwork(@Nullable EFFNetwork network);

    boolean hasNetwork();

    boolean isSourceMachine();

    int getMachineEfficiency();

    Set<Direction> getOutputSide();

    boolean canInput(Direction direction);

}
