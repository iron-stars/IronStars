package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface CanWrenchBlock {
    void onWrenched(Level world, BlockPos pos, BlockState state, Player player, Direction direction);

    default void onSneakWrenched(Level world, BlockPos pos, BlockState state, Player player, Direction direction) {
        world.removeBlock(pos, false);
        BlockEntity blockentity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropResources(state, world, pos, blockentity, player, ItemStack.EMPTY);
    }
}
