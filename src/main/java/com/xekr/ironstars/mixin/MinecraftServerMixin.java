package com.xekr.ironstars.mixin;

import com.xekr.ironstars.efficiency.EFFNetwork;
import net.minecraft.core.MappedRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(
            method = "createLevels",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;readScoreboard(Lnet/minecraft/world/level/storage/DimensionDataStorage;)V"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void readIronStarsState(
            ChunkProgressListener chunkProgressListener,
            CallbackInfo ci,
            ServerLevelData serverLevelData,
            WorldGenSettings worldGenSettings,
            boolean flag,
            long i,
            long j,
            List<CustomSpawner> list,
            MappedRegistry<LevelStem> mappedRegistry,
            LevelStem levelStem,
            ChunkGenerator chunkGenerator,
            DimensionType dimensionType,
            ServerLevel serverLevel,
            DimensionDataStorage dimensionDataStorage
    ) {
        dimensionDataStorage.computeIfAbsent(EFFNetwork.getState()::loadData, EFFNetwork.getState()::loadData, "networks");
    }

}
