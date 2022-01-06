package com.xekr.ironstars.mixin;

import com.xekr.ironstars.config.IronStarsConfig;
import com.xekr.ironstars.registry.IBlocks;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 slowDown(Vec3 instance, double p_82521_, double p_82522_, double p_82523_) {
        ItemEntity ths = (ItemEntity) (Object) this;
        if (ths.level.getBlockState(ths.blockPosition()).is(IBlocks.MAGNET_BLOCK.get())) p_82522_ /= IronStarsConfig.ItemInMagnetGravy;
        return instance.add(p_82521_, p_82522_, p_82523_);
    }
}
