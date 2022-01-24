package com.xekr.ironstars.mixin;

import com.mojang.serialization.Lifecycle;
import com.xekr.ironstars.world.MoonChunkGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@OnlyIn(Dist.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Redirect(method = "doLoadLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/WorldData;worldGenSettingsLifecycle()Lcom/mojang/serialization/Lifecycle;"))
    private Lifecycle isMoonWorld(WorldData instance) {
        return instance.worldGenSettings().dimensions().entrySet().stream().anyMatch(entry ->
                entry.getValue().generator() instanceof MoonChunkGenerator
        ) ? Lifecycle.stable() : instance.worldGenSettingsLifecycle();
    }

}
