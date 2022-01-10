package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface IWrenchBlock {
    InteractionResult onWrenched(BlockState state, UseOnContext context);

    default InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        if (!world.isClientSide) {
            if (player != null) {
                Block.getDrops(state, (ServerLevel) world, pos, world.getBlockEntity(pos), player, context.getItemInHand())
                        .forEach(itemStack -> player.getInventory().placeItemBackInInventory(itemStack));
            }
            state.spawnAfterBreak((ServerLevel) world, pos, ItemStack.EMPTY);
            world.destroyBlock(pos, false);
//            world.removeBlock(pos, false);
            // TODO 敲下方块后扳手发声
        }
        return InteractionResult.SUCCESS;
    }

    static Direction.Axis rotateAxisX(Direction.Axis axis) {
        return switch (axis) {
            case X -> Direction.Axis.X;
            case Y -> Direction.Axis.Z;
            case Z -> Direction.Axis.Y;
        };
    }

    static Direction.Axis rotateAxisY(Direction.Axis axis) {
        return switch (axis) {
            case X -> Direction.Axis.Z;
            case Y -> Direction.Axis.Y;
            case Z -> Direction.Axis.X;
        };
    }

    static Direction.Axis rotateAxisZ(Direction.Axis axis) {
        return switch (axis) {
            case X -> Direction.Axis.Y;
            case Y -> Direction.Axis.X;
            case Z -> Direction.Axis.Z;
        };
    }
    //TODO rotateDirectionX/Y/Z
}
