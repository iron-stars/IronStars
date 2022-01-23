package com.xekr.ironstars.world;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xekr.ironstars.registry.AllBiomes;
import com.xekr.ironstars.registry.AllDimensions;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;

public class BiomeProvider extends BiomeSource {
    public static final Codec<BiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.LONG.fieldOf("seed").stable().orElseGet(() -> AllDimensions.seed).forGetter(obj -> obj.seed),
            RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(provider -> provider.registry)
    ).apply(instance, instance.stable(BiomeProvider::new)));

    private static final List<ResourceKey<Biome>> BIOMES = ImmutableList.of(Biomes.DESERT);

    private final Registry<Biome> registry;
    private final Layer genBiomes;
    private final long seed;

    public BiomeProvider(long seed, Registry<Biome> registryIn) {
        super(BIOMES.stream().map(ResourceKey::location).map(registryIn::getOptional).filter(Optional::isPresent).map(opt -> opt::get));
        this.seed = seed;
        registry = registryIn;
        genBiomes = makeLayers(seed, registryIn);

    }

    public static int getBiomeId(ResourceKey<Biome> biome, Registry<Biome> registry) {
        return registry.getId(registry.get(biome));
    }

    private static <T extends Area, C extends BigContext<T>> AreaFactory<T> makeLayers(LongFunction<C> seed, Registry<Biome> registry, long rawSeed) {

        AreaFactory<T> biomes = GenLayerTFBiomes.INSTANCE.setup(registry).run(seed.apply(1L));
//        biomes = GenLayerTFKeyBiomes.INSTANCE.setup(registry, rawSeed).run(seed.apply(1000L), biomes);
//        biomes = GenLayerTFCompanionBiomes.INSTANCE.setup(registry).run(seed.apply(1000L), biomes);
//
//        biomes = ZoomLayer.NORMAL.run(seed.apply(1000L), biomes);
//        biomes = ZoomLayer.NORMAL.run(seed.apply(1001L), biomes);
//
//        biomes = GenLayerTFBiomeStabilize.INSTANCE.run(seed.apply(700L), biomes);
//
//        biomes = GenLayerTFThornBorder.INSTANCE.setup(registry).run(seed.apply(500L), biomes);
//
//        biomes = ZoomLayer.NORMAL.run(seed.apply(1002), biomes);
//        biomes = ZoomLayer.NORMAL.run(seed.apply(1003), biomes);
//        biomes = ZoomLayer.NORMAL.run(seed.apply(1004), biomes);
//        biomes = ZoomLayer.NORMAL.run(seed.apply(1005), biomes);
//
//        AreaFactory<T> riverLayer = GenLayerTFStream.INSTANCE.setup(registry).run(seed.apply(1L), biomes);
//        riverLayer = SmoothLayer.INSTANCE.run(seed.apply(7000L), riverLayer);
//        biomes = GenLayerTFRiverMix.INSTANCE.setup(registry).run(seed.apply(100L), biomes, riverLayer);

        return biomes;
    }

    public static Layer makeLayers(long seed, Registry<Biome> registry) {
        AreaFactory<LazyArea> areaFactory = makeLayers((context) -> new LazyAreaContext(25, seed, context), registry, seed);
        return new Layer(areaFactory) {
            @Override
            public Biome get(Registry<Biome> p_242936_1_, int p_242936_2_, int p_242936_3_) {
                int i = this.area.get(p_242936_2_, p_242936_3_);
                Biome biome = registry.byId(i);
                if (biome == null)
                    throw new IllegalStateException("Unknown biome id emitted by layers: " + i);
                return biome;
            }
        };
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
        return genBiomes.get(registry, p_186735_, p_186737_);
    }
}
