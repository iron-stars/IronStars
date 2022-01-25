package com.xekr.ironstars.mixin;

import com.xekr.ironstars.registry.AllBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LightningBolt.class)
public class LightingBoltMixin {
    @Inject(at = @At("RETURN"), method = "clearCopperOnLightningStrike", locals = LocalCapture.CAPTURE_FAILHARD)
    private static void toMagnet(Level world, BlockPos pos, CallbackInfo ci, BlockState lightState, BlockPos targetPos, BlockState targetState) {
        for (int i = 0; i < 16; i++) {
            BlockState blockState = world.getBlockState(targetPos);
            if (!blockState.is(Blocks.IRON_BLOCK))
                break;
            world.setBlockAndUpdate(targetPos, AllBlocks.MAGNET_BLOCK.defaultBlockState());
            targetPos = targetPos.relative(Direction.DOWN);
        }
    }
}
