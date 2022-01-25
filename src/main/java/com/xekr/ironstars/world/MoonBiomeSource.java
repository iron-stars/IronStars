package com.xekr.ironstars.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MoonBiomeSource extends BiomeSource implements BiomeManager.NoiseBiomeSource {
    public static final Codec<MoonBiomeSource> CODEC = Biome.CODEC.fieldOf("biome")
            .xmap(MoonBiomeSource::new, obj -> () -> obj.biome).stable().codec();

    private final Biome biome;

    private MoonBiomeSource(Supplier<Biome> biome) {
        this(biome.get());
    }

    public MoonBiomeSource(Biome biome) {
        super(ImmutableList.of(biome));
        this.biome = biome;
    }
    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public BiomeSource withSeed(long pSeed) {
        return this;
    }

    @Override
    public Biome getNoiseBiome(int p_186735_, int p_186736_, int p_186737_, Climate.Sampler p_186738_) {
        return this.biome;
    }

    @Override
    public Biome getNoiseBiome(int pX, int pY, int pZ) {
        return this.biome;
    }

    @Nullable
    @Override
    public BlockPos findBiomeHorizontal(int x, int y, int z, int radius, int blockCheckInterval, Predicate<Biome> predicate, Random random, boolean bl, Climate.Sampler sampler) {
        return predicate.test(this.biome) ? bl ? new BlockPos(x, y, z) : new BlockPos(x-radius+random.nextInt(radius*2+1), y, z-radius+random.nextInt(radius*2+1)) : null;
    }

    @Override
    public Set<Biome> getBiomesWithin(int p_186705_, int p_186706_, int p_186707_, int p_186708_, Climate.Sampler p_186709_) {
        return Sets.newHashSet(this.biome);
    }
}
