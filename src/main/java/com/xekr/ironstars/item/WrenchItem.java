package com.xekr.ironstars.item;

import com.xekr.ironstars.blocks.IWrenchBlock;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WrenchItem extends Item {
    public WrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        BlockState state = world.getBlockState(context.getClickedPos());
        if (player != null && !world.isClientSide && state.getBlock() instanceof IWrenchBlock block)
            return player.isShiftKeyDown() ? block.onSneakWrenched(state, context) : block.onWrenched(state, context);
        else return InteractionResult.PASS;
    }
}
