package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AllBiomes {
    private static final DeferredRegister<Biome> REGISTRY = DeferredRegister.create(ForgeRegistries.BIOMES, IronStars.ID);

    public static final Biome MOON = registerBiome("moon", builder -> builder
            .precipitation(Biome.Precipitation.NONE)
            .biomeCategory(Biome.BiomeCategory.NONE)
            .temperature(-0.5f)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .downfall(0.4f)
            .specialEffects(new BiomeSpecialEffects.Builder()
                    .skyColor(0)
                    .fogColor(12303291)
                    .waterColor(12303291)
                    .waterFogColor(12303291)
                    .grassColorOverride(12303291)
                    .foliageColorOverride(12303291)
                    .ambientMoodSound(new AmbientMoodSettings(AllSounds.DEEP_SPACE, 6000, 8, 2.0f))
                    .build())
            .generationSettings(new BiomeGenerationSettings.Builder().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AllFeatures.PLACED_TITANIUM_ORE).build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
    );

    private static Biome registerBiome(String name, ReturnSelfConsumer<Biome.BiomeBuilder> consumer) {
        Biome biome = consumer.accept(new Biome.BiomeBuilder()).build();
        REGISTRY.register(name, () -> biome);
        return biome;
    }

    @FunctionalInterface
    interface ReturnSelfConsumer<T> {
        void block(T t);

        default T accept(T t) {
            block(t);
            return t;
        }
    }

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
