package com.xekr.ironstars.mixin;

import com.xekr.ironstars.config.IronStarsConfig;
import com.xekr.ironstars.registry.AllBlocks;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 slowDown(Vec3 instance, double pX, double pY, double pZ) {
        ItemEntity ths = (ItemEntity) (Object) this;
        if (ths.level.getBlockState(ths.blockPosition()).is(AllBlocks.MAGNET_BLOCK.get())) pY /= IronStarsConfig.ItemInMagnetGravy;
        return instance.add(pX, pY, pZ);
    }
}
