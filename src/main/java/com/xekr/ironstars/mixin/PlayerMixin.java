package com.xekr.ironstars.mixin;

import com.xekr.ironstars.registry.AllItems;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(at = @At("RETURN"), method = "turtleHelmetTick",locals = LocalCapture.CAPTURE_FAILHARD)
    private void breathingHelmetTick(CallbackInfo ci, ItemStack itemstack) {
        Player ths = (Player) (Object) this;
        if (itemstack.is(AllItems.BREATHING_HELMET.get()) && ths.isEyeInFluid(FluidTags.WATER)) {
            ths.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0, false, true, true));
        }
    }
}
