package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AllBiomes {
    private static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, IronStars.ID);

//    public static final ResourceKey<Biome> MOON = registerBiome("moon");

    private static ResourceKey<Biome> registerBiome(String name) {
        BIOMES.register(name, () -> new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .biomeCategory(Biome.BiomeCategory.NONE)
                .downfall(0)
                .temperature(0)
                .specialEffects(new BiomeSpecialEffects.Builder().fogColor(0).waterColor(0).waterFogColor(0).skyColor(0).build())
                .generationSettings(new BiomeGenerationSettings.Builder().build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build());
        return ResourceKey.create(Registry.BIOME_REGISTRY, IronStars.id(name));
    }

    public static void register(IEventBus bus) {
        BIOMES.register(bus);
    }
}
