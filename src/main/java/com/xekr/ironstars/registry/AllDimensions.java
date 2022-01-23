package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.world.BiomeProvider;
import com.xekr.ironstars.world.ModChunkGenerator;
import net.minecraft.core.Registry;

public class AllDimensions {
    public static long seed;
    public static void init() {
        Registry.register(Registry.BIOME_SOURCE, IronStars.id("ironstars_biomes"), BiomeProvider.CODEC);

        Registry.register(Registry.CHUNK_GENERATOR, IronStars.id("structure_locating_wrapper"), ModChunkGenerator.CODEC);
    }

}
