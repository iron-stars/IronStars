package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TitaniumPressurePlateBlock extends PressurePlateBlock implements EntityBlock {
    public TitaniumPressurePlateBlock(PressurePlateBlock.Sensitivity pSensitivity, BlockBehaviour.Properties pProperties) {
        super(pSensitivity, pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }
}
