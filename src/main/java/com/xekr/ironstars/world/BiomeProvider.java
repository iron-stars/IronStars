package com.xekr.ironstars.world;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xekr.ironstars.registry.AllDimensions;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.Optional;

public class BiomeProvider extends BiomeSource {
    public static final Codec<BiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.LONG.fieldOf("seed").stable().orElseGet(() -> AllDimensions.seed).forGetter(obj -> obj.seed),
            RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(provider -> provider.registry)
    ).apply(instance, instance.stable(BiomeProvider::new)));

    private static final List<ResourceKey<Biome>> BIOMES = ImmutableList.of(Biomes.DESERT);

    private final Registry<Biome> registry;
    private final long seed;

    public BiomeProvider(long seed, Registry<Biome> registryIn) {
        super(BIOMES.stream().map(ResourceKey::location).map(registryIn::getOptional).filter(Optional::isPresent).map(opt -> opt::get));
        this.seed = seed;
        registry = registryIn;

    }

    public static int getBiomeId(ResourceKey<Biome> biome, Registry<Biome> registry) {
        return registry.getId(registry.get(biome));
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public BiomeSource withSeed(long pSeed) {
        return new BiomeProvider(pSeed, registry);
    }

    @Override
    public Biome getNoiseBiome(int p_186735_, int p_186736_, int p_186737_, Climate.Sampler p_186738_) {
        return OverworldBiomes.desert();
    }
}
