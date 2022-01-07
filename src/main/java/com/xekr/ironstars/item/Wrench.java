package com.xekr.ironstars.item;

import com.xekr.ironstars.blocks.CanWrenchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Wrench extends Item {
    public Wrench(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) { //TODO 对着方块使用
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Player player = context.getPlayer();
        if (!world.isClientSide && state.getBlock() instanceof CanWrenchBlock block) {
            if (player.isShiftKeyDown()) {
                block.onSneakWrenched(world, pos, state, player, context.getClickedFace());
            } else {
                block.onWrenched(world, pos, state, player, context.getClickedFace());
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}
