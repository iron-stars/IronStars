package com.xekr.ironstars.mixin;

import com.xekr.ironstars.recipe.AnvilFlatteningCraftingManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;levelEvent(ILnet/minecraft/core/BlockPos;I)V"), method = "onLand")
    private void onLand(Level level, BlockPos blockPos, BlockState state, BlockState targetState, FallingBlockEntity entity, CallbackInfo ci) {
        AnvilFlatteningCraftingManager.craft(level, entity.blockPosition());
    }
}
